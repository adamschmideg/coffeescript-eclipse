
This is a project to provide an Eclipse plugin for [CoffeeScript][coffeescript], using [Xtext][xtext].
Development uses Xtext 2.1.
It works as a regular Eclipse plugin (see Installation for details).
Highlights include

 - syntax highlighting
 - variable autocompletion in the current namespace
 - autoindent


# Status
It's aimed at being mostly compatible with the original CoffeeScript project.
There are some extra features and some missing,
 but you probably (and hopefully) won't notice the difference in everyday use.

## Extra features
CoffeeScript is a dyanmic language,
 the parser doesn't check the types or even the existence of variables,
 such mismatches are detected at runtime.
This plugin is based on Xtext which is geared to static languages,
 providing some useful tools for dealing with these issues.
Doing proper type inference for such a flexible language would be difficult,
 but there are some cases where more checking can be done than by the original CoffeeScript parser.
Consider this snippet

    foo = 1
    bar = foo + increment

It is perfectly valid code, the `increment` variable is undefined, though.
This plugin will issue a warning about a reference to an unknown variable.
It works within string interpolation, too, so the next snippet will also give a warning

    console.log "Incremented by ${ increment }"

Note that the `console` variable won't cause any warning, it's handled as a built-in variable.

## Incompatibilities
The plugin handles properly most language constructs,
 including all examples in the coffeescript documentation folder.
There are two cases it cannot handle, though, post-increment and
assignment to a deeply nested property.

    # Not working
    drinkBeer(glasses++)  

    # Workaround
    drinkBeer(glasses)
    glasses += 1

    # Not working
    my.secret.money = 1000

    # Workaround
    tmp = my.secret
    tmp.money = 1000

I guess they don't hurt that much...
Getting the value of a deeply nested property is OK.

    # This is working
    borrow(my.secret.money)
    borrowed = my.secret.money

## Planned features
I have a few in my mind, you can add more at issues.
Current plans include
 - integrated build: conversion to javascript
 - extensible plugin: use CoffeeScript as a core language for your DSL

# Installation
You will need an Eclipse instance with Xtext plugins.
You can either install a complete Indigo distribution with Xtext,
 or install the required plugins into your existing workspace.
See [download Xtext 2.1][xtext_download] for details.

Then perform these steps

 1. Download or check out this project to `<someFolder>`.
   (Note: the most recent version can be found at
   https://bitbucket.org/adamschmideg/coffeescript-eclipse/downloads
   The repository gets mirrored at https://github.com/adamschmideg/coffeescript-eclipse with some delay.)
 1. Import the subprojects into your workspace.
     -  `File -> Import... -> General / Existing Projects into Workspace`
     - `Next`
     - `Select root directory`: select the folder where you downloaded the whole stuff
     - Select all projects starting with `csep.` under `Projects:` 
     - `Finish`
 1. Create plugin locally
     - Open the `csep.update` project in the package explorer
     - Open `site.xml` in it (it will open a custom editor)
     - Click `Synchronize...` (all features)
     - Click `Build all`.
   This will populate the `csep.update` directory with all the artifacts.
 1. Install plugin
     - `Help -> Install New Software...`
     - `Add...` location: select the `csep.update` directory
     - Select `Coffeescript editor`
     - `Next` and `Finish`


I'm going to set up a proper update site to make installation easier.
Apologies for the inconveniences until then.


  [coffeescript]: http://www.coffeescript.org
  [xtext]: http://www.xtext.org
  [xtext_download]: http://www.eclipse.org/Xtext/download/
