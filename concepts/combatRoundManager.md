Concept of the combat round manager


1. General
  - Nur der Manager fragt Werte bei Charakteren ab (z.B. Initiative-Wurf, Angriffswurf, Schadenswurf, etc.)
    v.A. anhand von Workflows (die Abfragen sind in den Workflows definiert)
  - Der Manager erwartet Aktionen (Interaction Object) von demjenigen Charakter, der gerade am Zug ist
  - Es kann vom Manager stets abgefragt werden, welcher Teilnehmer gerade am Zug ist
  - Aktionen von Teilnehmern, die nicht am Zug sind, werden abgelehnt

1. The Interaction Object
  - type of interaction, e.g. attack, full attack, etc. The type contains the workflow definition of how to handle the interaction
  - list of target characters
  - the triggering character (or source character)
  - for an attack the mode of attack (e.g. primary hand melee attack, etc.) must be specified in the character's registry.

1. The Uber-Workflow

  1. Organize the beginning of the combat encounter
    1. Instanz des Managers bekommt Liste von Kampfteilnehmern bei Instanziierung übergeben
    1. Initiative-Workflow durchführen:
      1. Abfrage der Initiative-Würfe
      1. Bei Gleichstand gilt höherer Bonus
      1. Bei Gleichstand gilt nochmal Würfeln bis höheres Ergebnis eindeutig
    1. Charaktere mit Zustand flat footed versehen

  1. Characters can now commit their interactions in their move.
     Aktionen abhandeln anhand von Workflows (aktionsspezifische Workflows, d.h. definiert im Typ der Aktion)

  1. Nach dem Zug jeweils Zustände setzen bzw. aufheben
     Beispiele: TP eines Charakters = 0 --> Zustand kampfunfähig
                TP eines Charakters < 0 --> Zustand bewusstlos
                Zustand eines Charakters läuft aus (hielt z.B. nur 5 Runden, etc.)

