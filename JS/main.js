console.log("Welcome to the Community Portal");

const eventName = "Summer Street Concert";
const eventDate = "2026-07-15";
let availableSeats = 34;

window.addEventListener("load", () => {
  alert("Community Portal is fully loaded");
  initPortal();
});

class Event {
  constructor({ id, name, date, location, category, seats, description }) {
    this.id = id;
    this.name = name;
    this.date = new Date(date);
    this.location = location;
    this.category = category;
    this.seats = seats;
    this.description = description;
  }
}

Event.prototype.checkAvailability = function () {
  return this.seats > 0 && this.date >= new Date();
};

const events = [];
let displayedEvents = [];

const registrationTracker = createCategoryRegistrationTracker();

function initPortal() {
  const registerBtn = document.querySelector("#registerBtn");
  registerBtn.addEventListener("click", () => {
    console.log("Register button clicked");
  });

  $("#registerBtn").click(() => {
    console.log("jQuery detected register click");
  });

  const categoryFilter = document.querySelector("#categoryFilter");
  const searchInput = document.querySelector("#searchInput");
  const form = document.querySelector("#registrationForm");

  categoryFilter.onchange = () => renderFilteredEvents(categoryFilter.value, searchInput.value);
  searchInput.onkeydown = (event) => {
    if (event.key === "Enter") {
      event.preventDefault();
      renderFilteredEvents(categoryFilter.value, searchInput.value);
    }
  };

  form.addEventListener("submit", handleRegistrationForm);

  loadEvents();
}

function createCategoryRegistrationTracker() {
  const totals = {};
  return function track(category) {
    totals[category] = (totals[category] || 0) + 1;
    return { category, total: totals[category] };
  };
}

async function loadEvents() {
  const spinner = document.querySelector("#loadingSpinner");
  spinner.style.display = "block";

  try {
    const response = await fetch("events.json");
    if (!response.ok) throw new Error("Unable to fetch events");
    const data = await response.json();
    const newEvents = data.map((item) => new Event(item));
    events.push(...newEvents);
    displayedEvents = [...events];
    updateEventOptions();
    renderFilteredEvents("all", "");
  } catch (error) {
    console.error("Failed to load event data", error);
    document.querySelector("#eventsContainer").textContent = "Unable to load events.";
  } finally {
    spinner.style.display = "none";
  }
}

function addEvent(eventData) {
  const eventObj = new Event(eventData);
  events.push(eventObj);
  return eventObj;
}

function updateEventOptions() {
  const select = document.querySelector("select[name=eventId]");
  select.innerHTML = "";
  const available = events.filter((item) => item.checkAvailability());
  available.forEach((event) => {
    const option = document.createElement("option");
    option.value = event.id;
    option.textContent = `${event.name} (${event.location})`;
    select.appendChild(option);
  });
}

function renderFilteredEvents(category, query) {
  const container = document.querySelector("#eventsContainer");
  container.innerHTML = "";

  const normalizedQuery = query.trim().toLowerCase();
  const filtered = events.filter((event) => {
    const isCategoryMatch = category === "all" || event.category === category;
    const isQueryMatch = !normalizedQuery || event.name.toLowerCase().includes(normalizedQuery);
    return event.checkAvailability() && isCategoryMatch && isQueryMatch;
  });

  displayedEvents = [...filtered];

  if (filtered.length === 0) {
    container.innerHTML = "<p>No valid upcoming events found.</p>";
    return;
  }

  filtered.forEach((event) => {
    const card = createEventCard(event);
    container.appendChild(card);
    $(card).fadeIn(300);
  });
}

function createEventCard(event) {
  const card = document.createElement("article");
  card.className = "event-card";
  card.dataset.eventId = event.id;

  const dateString = event.date.toLocaleDateString(undefined, { year: "numeric", month: "long", day: "numeric" });
  card.innerHTML = `
    <h3>${event.name}</h3>
    <p class="badge">${event.category}</p>
    <p>${event.description}</p>
    <p><strong>Date:</strong> ${dateString}</p>
    <p><strong>Location:</strong> ${event.location}</p>
    <p><strong>Seats left:</strong> <span class="seat-count">${event.seats}</span></p>
    <button type="button" onclick="registerUser(${event.id})">Register</button>
    <details>
      <summary>Event data</summary>
      <pre>${formatObjectEntries(event)}</pre>
    </details>
  `;

  return card;
}

function formatObjectEntries(event) {
  return Object.entries(event)
    .map(([key, value]) => `${key}: ${value instanceof Date ? value.toISOString().split("T")[0] : value}`)
    .join("\n");
}

function registerUser(eventId) {
  try {
    const event = events.find((item) => item.id === eventId);
    if (!event) throw new Error("Event not found");
    if (!event.checkAvailability()) throw new Error("Registrations are closed for this event.");

    event.seats--;
    availableSeats--;

    const registrationInfo = registrationTracker(event.category);
    console.log(`Registered for ${event.name}. Total ${event.category} registrations: ${registrationInfo.total}`);

    updateEventOptions();
    renderFilteredEvents(document.querySelector("#categoryFilter").value, document.querySelector("#searchInput").value);
    showFormMessage(`Registration confirmed for ${event.name}.`, true);
  } catch (error) {
    console.error("Registration error", error);
    showFormMessage(error.message, false);
  }
}

function handleRegistrationForm(event) {
  event.preventDefault();
  const form = event.currentTarget;
  const name = form.elements.name.value.trim();
  const email = form.elements.email.value.trim();
  const selectedEventId = Number(form.elements.eventId.value);

  if (!name || !email || !selectedEventId) {
    return showFormMessage("Please fill in all fields before submitting.", false);
  }

  const payload = { name, email, eventId: selectedEventId };
  showFormMessage("Submitting registration...", true);
  postRegistrationData(payload)
    .then((json) => {
      console.log("Registration response", json);
      registerUser(selectedEventId);
    })
    .catch((error) => {
      showFormMessage(`Registration failed: ${error.message}`, false);
    });
}

function showFormMessage(message, success) {
  const messageBox = document.querySelector("#formMessage");
  messageBox.textContent = message;
  messageBox.className = `message ${success ? "success" : "error"}`;
}

function postRegistrationData(data) {
  return fetch("https://jsonplaceholder.typicode.com/posts", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) throw new Error("Network response was not ok");
      return response.json();
    })
    .then((result) => new Promise((resolve) => setTimeout(() => resolve(result), 800)));
}

function filterEventsByCategory(callback) {
  return events.filter(callback);
}

const musicEvents = filterEventsByCategory((event) => event.category === "Music");
const musicCards = musicEvents.map((event) => `${event.name} — ${event.location}`);
console.log("Music event cards:", musicCards);

const infoSummary = `Event summary: ${eventName} on ${eventDate}. Available seats before registration: ${availableSeats}`;
console.log(infoSummary);

addEvent({ id: 5, name: "Arts in the Park", date: "2026-08-03", location: "Maple Park", category: "Community", seats: 25, description: "Family-friendly arts activities." });
