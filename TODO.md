## Unarmed Strike And Nonlethal Damage
 - [ ] inflict nonlethal damage with normal weapon
 - [ ] inflict lethal / nonlethal damage with unarmed strike
 - [ ] global AND per-weapon setting of lethal/nonlethal damageLethality in the registry. the per-weapon settings overrides the global value. Default is lethal for normal weapons and nonlethal for unarmed strikes.
 - [ ] have unarmed strike being part of the race's body slot layout
 - [ ] count nonlethal hp in the character

## Event Based Condition Gathering
 - [ ] When HPs are reduced, throw an event according to the resulting hp
 - [ ] 0 hp: reachedZeroHitPointsEvent --> character becomes disabled
 - [ ] -1 <= hp <= -9: [think up some nice event name] --> character becomes dying
 - [ ] hp <= -10: [think up some nice event name] --> charcters becomes dead
 - [ ] implement all those new conditions mentioned above

## Auto Actions At Combat
 - [ ] at the beginning of a combat round, the list of the character's auto actions should be performed.
 - [ ] Example: the stabilisation roll when < 0 hp and dying. The player has no chance not to make this roll
 - [ ] A dead character has the auto-action of ending the turn.

## Unusually Sized Items
 - [ ] Weapons / Armor of size category other than medium
 - [ ] automatic negative boni for the usage of items of the wrong size category
 - [ ] unarmed strike for creatures other than medium sized

## Character Creation Validation
 - [ ] System of XML-based rules (Spring application context xml) to verify if a character is conform to the rules

## Rogue Character Class
 - [ ] Sneak attack, also in the workflow (e.g. damageWorkflow)

## Hitpoint Improvements
 - [ ] Introduce temporary hitpoints and adjust the damageApplicationService accordingly (appliySingleDamage method): subtract from temporary hit points first! (e.g. spell false life)
 - [ ] hit points gained during barbarian rage, for example, are also treated differently - they are NOT used first like temporary hit points are!

## Feat Implementations
 - [ ] Dodge - AC changes against designated opponent
 - [ ] Power Attack - only when STR >= 13
 - [ ] Power Attack - bonus within correct range -baseAtkBonus <= n <= 0
 - [ ] Power Attack - not weapon specific in state registry!!! - must be verified, perhaps it is already that way

## Touch Attack Support
 - [ ] Consider AC calculation. AcCalculationService.java, calcAcStandard().

## Item Stuff
 - [ ] Armor that can be used as weapons and vice versa
 - [ ] Unwielded / unworn weapons and armor - they contribute to nothing, e.g. to AC!
 - [ ] Test correct calculation of max dexterity bonus of armor in combat
 - [ ] Two characters cannot wear or wield the same item at once.
 - [ ] Speed Shift of armor: 30 ft. --> 20 ft. via observers

## Natural Weapons Support
 - [ ] consider this in Weapon Finesse feat: WeaponFinesseObserverTrigger.java, determineSituationContextValidity().

## Misc. Engine Technology
 - [ ] Secure entry points of the engine by assertions (e.g. spring assertion framework?) - it is not wise to end up with a nullpointer exception deep in the guts of the engine and it would take hell of a time to debug this and trace the origin of the problem
 - [ ] Introduce a global date in the CombatRound (year, day of year, hour of day, minutes and seconds) as an offset for when the combat round starts. Then the null-return of getCurrentDate() can be replaced by that date!
 - [ ] Required properties of setup classes definable in spring xml
 - [ ] Create RollHistoryToCampaignListener and use it in retrieveRollResult() of the DefaultRollResultManager instead of the campaign.addRollHistoryEntry method call.
 - [ ] Handle default values in the situation context service correctly and centralized.
 - [ ] add further sources of feats to the dndCharacter. E.g. class levels. DndCharacter.java, getFeatBindingsByFeatType().
 - [ ] an attack could inflict multiple types of damage, e.g. +1d6 poison, etc. So we deliver a list of damages. DamageWorkflow.java, runWorkflow().
 - [ ] verify if combat round has a current date when it has just started. The method call combatRound.getCurrentDate() could be problematic because the combat round might not yet have a current date when it has just started and everybody rolls initiative! InitializeCombatRoundWorkflow.java, rollInitiative().
 - [ ] remove the limitation that a dynamic value provider can only provide single-ranked boni (that means with rank 0). DefaultBonusCalculationServiceImpl.java, getEffectiveValues().

