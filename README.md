# Mobile Engineer Candidate Code Exercise

## Requirements
- App Requirements
    - Write in Kotlin for Android
    - Use XML layouts for Android (not Compose)
- Sample app
    - Fetch and display data from RESTful Web API
    - Consist of two parts: list and detail
    - Support portrait and landscape orientations for phones and tablets
- On Phones
    - Separate screens for list and detail
- On Tablets
    - List and detail on the same screen
- List view
    - Display text-only, vertically scrollable list of character names
- Search functionality
    - Filter character list based on query text in titles or descriptions
- Detail view
    - Show character image, title, and description
    - Use "Icon" field URL from API JSON response for image
    - Use placeholder image for items with missing image URLs
- Two variants with shared codebase
    - Different name, package-name, and data API URL
- Variant One
    - Name: Simpsons Character Viewer
    - Data API: http://api.duckduckgo.com/?q=simpsons+characters&format=json
    - Package/Bundle name: com.sample.simpsonsviewer
- Variant Two
    - Name: The Wire Character Viewer
    - Data API: http://api.duckduckgo.com/?q=the+wire+characters&format=json
    - Package/Bundle name: com.sample.wireviewer

## Build and Run
- Download or clone the repository from GitHub
- Open the project in Android Studio
- Build and run the project on a device of choice
