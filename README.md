# What Does This Plugin Do?

```LESS Compiler``` monitors [LESS](http://lesscss.org/) files and automatically compiles them to CSS whenever they change.

## IDE Compatibility

This plugin is **ONLY** compatible with IntelliJ IDEA 11+, PhpStorm 5+, and WebStorm 5+.
It should also be compatible with RubyMine 4.5+, but has not been tested.

## Features

1.  **Recursive Directory Monitoring**

    ```LESS Compiler``` watches directories (and subdirectories) for changes to LESS files and automatically compiles them
    to CSS when they are saved in the editor (or when IntelliJ detects that they were modified outside the IDE).

    You can monitor as many LESS directories as you like.  You can also specify as many output directories as you like
    so that compiled CSS files will be copied to multiple locations (e.g., a local ```src``` directory under version control
    and a mounted ```target``` directory on a remote server).

    The directory structure of the output CSS directories will be identical to the structure of the source LESS directory.

2.  **@import Dependency Resolution**

    Files that ```@import``` a modified LESS file will be re-compiled automatically.

    For example, if ```home.less```, ```about.less```, and ```contact.less``` all ```@import "common.less"```,
    modifying ```common.less``` will cause all three dependents to be re-compiled as well.

3.  **Include / Exclude File Patterns**

    Prevent specific LESS files from being compiled by specifying include / exclude patterns (glob) that match
    against filename, folder name, or any part of the complete path to the LESS file.

4.  **Move, Copy, and Delete Detection**

    When a LESS file is moved, copied, or deleted, ```LESS Compiler``` will offer to do the same to the corresponding CSS file(s).

5.  **Virtual Filesystem Notifications**

    Unlike other solutions, this plugin is smart enough to notify IntelliJ when CSS files are changed, moved, copied, or deleted.
    In most cases, updated CSS files will be immediately reflected in the editor and Project tree view.

6.  **Selective Compilation**

    If the plugin somehow fails to catch changes to a LESS file, simply right-click anywhere in the editor or Project tree
    and select "Compile to CSS".  You can also compile an entire directory by right-clicking on it in the Project tree.

7.  **Error Notifications**

    Any errors encountered during the compilation process will produce an error notification balloon in the IDE
    containing a link to the file and the line number that caused the error.

# Usage

## Installation

1.  Go to File > Settings (Windows / Linux) or IntelliJ IDEA > Preferences (Mac)
2.  Install the plugin from the IntelliJ plugin repository
3.  Restart the IDE

## Configuration

1.  Go to File > Settings (Windows / Linux) or IntelliJ IDEA > Preferences (Mac)
2.  Under Project Settings, select LESS Compiler
3.  Click the "+" button to add a new LESS profile
4.  Choose a LESS source directory
5.  Add one or more CSS output directories and click OK
6.  Make changes to a LESS file and save it
7.  Rejoice!

## Directory Structure

```LESS Compiler``` allows you to maintain arbitrarily complex directory structures with ease.
For example, suppose we have a project with the following directory structure ([LESS CSS Maven Plugin][lesscss-maven-source]'s default layout):

    projectRoot/
      +  src/main/
      |  +  less/
      |  |  +  common/
      |  |  |  -  common.less
      |  |  |  -  layout.less
      |  |  |  -  reset.less
      |  |  +  home/
      |  |  |  -  home.less
      |  |  +  checkout/
      |  |  |  -  checkout.less
      |  |  |  -  billing.less
      |  |  |  -  payment.less
      |  +  webapp/
      |  |  +  media/
      |  |  |  +  css/
      |  |  |  |  +  common/
      |  |  |  |  +  home/
      |  |  |  |  +  checkout/
      +  target/
      |  +  media/
      |  |  +  css/
      |  |  |  +  v2/
      |  |  |  |  +  common/
      |  |  |  |  +  home/
      |  |  |  |  +  checkout/

Such a structure would be impossible to maintain using other tools.  With ```LESS Compiler```, it's a breeze.

# Known Issues

*   **Slow First Compile**

    The first time you update a ```.less``` file it will take a few seconds to compile.
    This is because ```LESS Compiler``` uses the [Rhino][rhino] JavaScript engine to run ```less.js```, and Rhino
    takes a while to initialize.  But don't worry - after the initial compilation, all future compiles should complete in < 1 sec.

# Alternatives

Notable alternatives to this plugin:

*  [SimpLESS][simpless] (Win / Mac) - free

# Developers

## Running / Debugging the Plugin

**NOTE**: These instructions are out of date.  They will be updated soon.

1.  ```git clone git://github.com/acdvorak/intellij-lessc-plugin.git```
2.  In IntelliJ, go to File > Open Project... and select ~~```$PROJECT_DIR/lessc-plugin/lessc-plugin.iml```~~
3.  Create a Run / Debug configuration for the plugin module
4.  Test the plugin by going to Run > Run lessc-plugin

## LESS Compiler Version

This plugin uses version 1.3.3 of the official ```less-rhino.js``` compiler from [lesscss.org][lesscss].

# Credits

1.  **[LESS Engine][lesscss-engine]**: Asual DZZD ([GitHub project][lesscss-engine-source])
2.  **[JavaScript Engine][rhino]**: Mozilla Rhino ([GitHub project][rhino-source])
3.  **[LESS Compiler][lesscss]**: Alexis Sellier ([GitHub project][lesscss-source])
4.  **[IntelliJ Plugin][plugin]**: Andy Dvorak ([Github project][plugin-source])

# Copyright and License

Copyright 2012-2013 Andrew C. Dvorak.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[lesscss]: http://lesscss.org/
[lesscss-source]: https://github.com/cloudhead/less.js
[cloudhead]: http://cloudhead.io/

[lesscss-engine]: http://www.asual.com/lesscss
[lesscss-engine-source]: https://github.com/asual/lesscss-engine

[rhino]: https://developer.mozilla.org/en-US/docs/Rhino
[rhino-source]: https://github.com/mozilla/rhino

[simpless]: http://wearekiss.com/simpless
[lesscss-maven-source]: https://github.com/marceloverdijk/lesscss-maven-plugin

[plugin]: http://plugins.intellij.net/plugin?pr=&pluginId=7059
[plugin-source]: https://github.com/acdvorak/intellij-lessc-plugin
