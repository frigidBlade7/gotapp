# GOT app
Demo app showcasing the GOT api for Zatec. Winter is coming..

## libraries used
Mostly Jetpack recommended libraries
- Hilt (for dependency injection)
- Fragment, lifecycle, core ktx (for kotlin sweetness)
- Paging runtime (for loading paged data)
- Splashscreen (oohh ui goodness)
- Constraint, Material (for layouts and theming)
- Navigation fragment, ui (for app navigation)
- Moshi (for json parsing)
- Retrofit (for rest api calls)
- Okhttp (for exposing api responses to logcat)
- Swipe refresh (for pull to refresh functionality)
- Timber (I snuck this in to replace Log.)

## architecture
single activity with feature based modularisation 
#### modules include:
- core
- books
- houses
- characters

** Modules have similar implementation, but with some differences in implementation around paging and caching. Notably, only characters module has tied in room integration. Other module implementations remain as stubs

## Special thanks
Figma Community members for [this resource](https://www.figma.com/file/8qezNhcPOohBVeBC9oamOi/Game-of-Thrones-Family-Tree-(Community)?node-id=189%3A6073&t=uGGQgny45zwczolk-0) and [this one](https://www.figma.com/file/2RzIOoCODcr0fGHqfhWfym/Teste-Game-of-Thrones-Community?is-community-duplicate=1&fuid=) as well
Font Awesome for [these icons](http://fontawesome.com)
