# Neocortex Engine

The Neocortex Engine is a Java-based implementation of the d20 System, a role-playing game system published by Wizards of the Coast for the 3rd edition of Dungeons & Dragons.
It's an API that let's you manage characters, items (like weapons, armor, etc.) or spells and enforces the rules when these entities interact, for example during a combat round.
Furthermore it is a framework that let's you design your own game entities, like more character classes, skills, feats or even monsters.
It aims to implement all such elements given in the d20 system reference document in the future. For an overview over the rule system you can visit [The Hypertext d20 SRD](http://www.d20srd.org/) which is a very good resource.

The Neocortex Engine is **not** a role playing game in itself. It is the rules engine that can be used to power a game implementation legislatively. However, you can nonetheless play a little with it and have some fun on a textual basis, like it is shown in the JUnit integration tests. The implementation of such a game is planned for the future as an independent project.

## Getting started

Compile the application, run all tests and create a maven site which contains a large number of interesting reports about the software quality.

```
mvn clean compile integration-test site
```

After that you can do a [SonarQube](http://www.sonarqube.org/) scan of the project.

```
mvn sonar:sonar
```

### Logs

Several log files are produced in the base folder during the test run:

 * ```logFile.log```: Technical logs of the application.
 * ```eventsLog.log```: Tells the story of what is happening in the game's interactions in the form of events.

### Example Output

The integration tests currently implemented simulate a simple combat round between three combatants. Feel free to add and/or modify the tests to make the event history more epic!

```
Starting a combat round with the participants: harsk, merisiel, valeros.
Roll History: harsk rolls for initiative: 1d20+2 = 7.
Roll History: merisiel rolls for initiative: 1d20+3 = 23.
Roll History: valeros rolls for initiative: 1d20+6 = 14.
harsk gains condition: flatFooted.
valeros gains condition: flatFooted.
The participants act in the following order: merisiel, valeros, harsk.
merisiel attacks valeros with mwkRapier.
Roll History: merisiel rolls for attack (with mwkRapier): 1d20+4 = 23.
Roll History: merisiel rolls for attack (with mwkRapier): 1d20+4 = 20.
merisiel hits valeros critically (AC 15).
Roll History: merisiel rolls for damage (with mwkRapier): 1d6+1 = 5.
merisiel inflicts piercing damage on valeros for 5 hp.
valeros loses 5 hp and is now at 6 hp.
Roll History: merisiel rolls for damage (with mwkRapier): 1d6+1 = 4.
merisiel inflicts piercing damage on valeros for 4 hp.
valeros loses 4 hp and is now at 2 hp.
```

## Completion Status

|Races|
|-----|
|Dwarf| 5% |
|Elf  | 5% |
|Human| 5% |

|Classes|
|-------|
|Druid  | 1% |
|Fighter| 5% |
|Rogue  | 5% |

|Feats|
|-----|
|Armor Proficiency, Light   | 67% |
|Armor Proficiency, Medium  | 67% |
|Armor Proficiency, Heavy   | 67% |
|Improved Initiative        | 90% |
|Power Attack               | 67% |
|Shield Proficiency, Light  | 67% |
|Shield Proficiency, Heavy  | 67% |
|Shield Proficiency, Tower  | 67% |
|Weapon Finesse             | 67% |
|Weapon Proficiency, Simple | 67% |
|Weapon Proficiency, Martial| 67% |

|Weapons|
|-------|
|Bastardsword | 50% |
|Battleaxe    | 50% |
|Club         |  1% |
|Dagger       | 50% |
|Dart         |  5% |
|Longbow      | 50% |
|Longsword    | 50% |
|Quarterstaff |  1% |
|Rapier       | 50% |
|Sap          |  1% |
|Shortbow     |  1% |
|Shortsword   |  1% |
|Sling        |  5% |
|Spiked Chain |  1% |
|Whip         |  1% |

|Armor|
|-----|
|Chainmail            | 50% |
|Half Plate           | 50% |
|Steel Shield, Heavy  | 50% |
|Studded Leather      | 50% |
|Wooden Shield, Light | 50% |


|Conditions  |
|------------|
|Flat Footed | 90% |


Something not mentioned here has not been started yet.

## Future Aims

* Datastore for persisting the objects.
* Implementation of the [d20 Pathfinder SRD](http://www.d20pfsrd.com/) as an alternative RPG rule engine.
* 3D Frontend and having an actual game. I was thinking of utilizing [libGDX](http://libgdx.badlogicgames.com/) for this task, as it is also Java based.

## Project Naming

Here is what [Wikipedia](https://en.wikipedia.org/wiki/Neocortex) says about the Neocortex:
> In humans, the neocortex is involved in higher functions such as sensory perception, generation of motor commands, spatial reasoning, conscious thought and language.

For me this just seemed quite fitting for the purpose of this engine.

## Contributing

Help on this project is always appreciated. There are several topics for support:

 * Unit and Integration Tests - to make the rule engine waterproof. This is especially valuable when you are already familiar with the D20 system, e.g. when you're a Dungeons and Dragons Pro!
 * More entity definitions
 * Java Code

A secondary aim is to keep the code quality as high as possible. So don't hesitate to fire up your SonarQube or consult the various reports created by the maven site goal. The following tools are used for this:

 * Checkstyle
 * PMD
 * Findbugs
 * SonarQube

Also check the following code metrics when contributing code in the form of pull requests:

 * Code Duplication
 * Method Complexity (cyclomatic complexity)
 * Package Tangle Index
 * Unit test coverage

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).
