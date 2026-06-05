# Local Community Event Portal — HTML5 Exercises

Files:
- index.html — main exercise file implementing navigation, form, gallery, media, geolocation, and storage.

Quick start:

1. Open the file in Chrome (Windows):

```powershell
start chrome "${PWD}\\index.html"
```

Or open `index.html` directly from Explorer.

2. Use Chrome DevTools: Right-click → Inspect. Check Console for logs, set breakpoints in Sources, and edit styles in Elements.

Notes:
- The form shows a confirmation via the `<output>` element on submit.
- The page saves preferred event type in `localStorage` and pre-selects it on reload.
- Double-click gallery images to enlarge; video shows "Video ready to play" when ready.
- Clicking "Find Nearby Events" requests geolocation (permissions required).
