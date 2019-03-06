# New home!
I am happy to announce that this project is becoming a part of something bigger 
and has a new home at [https://github.com/Nodeclipse/coffeescript-eclipse](https://github.com/Nodeclipse/coffeescript-eclipse).

# Intro

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
CoffeeScript is a dynamic language,
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

## Changelog

<dl>
  <dt>0.2.2</dt>
  <dd>Embed coffeescript in a DSL (see the `example` directory)</dd>
  <dt>Planned next release</dt>
  <dd>Integrated build: convert coffee code to javascript, and run it</dd>
</dl>

# Installation
You will need an Eclipse instance with Xtext plugins.
You can either install a complete Indigo distribution with Xtext,
 or install the required plugins into your existing workspace.
See [download Xtext 2.1][xtext_download] for details.

The update site is: **`http://coffeescript-editor.eclipselabs.org.codespot.com/hg/`**
So in Eclipse, perform these steps

 - `Help -> Install New Software...` 
 - `Add...`, then use this url as Location
 - `Work with...`, and choose the location you just added
 - Select `Coffeescript editor`
 - `Next` and `Finish`

You may be [given a warning](https://bitbucket.org/adamschmideg/coffeescript-eclipse/issue/6/),
 but that won't affect the plugin.

  [coffeescript]: http://www.coffeescript.org
  [xtext]: http://www.xtext.org
  [xtext_download]: http://www.eclipse.org/Xtext/download.html
  [csep_bitbucket_download]: https://bitbucket.org/adamschmideg/coffeescript-eclipse/downloads
  [csep_github]: https://github.com/adamschmideg/coffeescript-eclipse 
