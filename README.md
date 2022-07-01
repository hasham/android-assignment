# translation app - Android Take Home Project

## Before you start

- Make sure you have the latest stable version of Android Studio and have a way to connect to a GitHub repository. You should be ready to run code from a repo on a physical device or simulator.

## Overview

You have been asked to create a translation app! 

Following are the things required:

1: create a screen with input where you'd enter text-to-be-translated

2: a text view where the translated text of the text you entered will be populated

3: a dropdown that will have available translation languages available from the APIs

4: a button at the end, upon clicking the button, it will show translated result in the view you created in point 2.


We would like you to use LibreTranslate API to implement the translation functionality. The documentation for the API is here: [https://libretranslate.com/docs/](https://libretranslate.com/docs/)


Your tasks are as follows:

- Fetch the list of languages supported by the LibreTranslate API (use `https://libretranslate.de/` as API Root, this version does not require an auth token). The UI is already set up to allow the user to pick from a list of languages provided by the view model.
- Implement the translation functionality and hook it up to the UI.
- structure the code and architecture as if you're working on a large scale project using the best coding practices.
