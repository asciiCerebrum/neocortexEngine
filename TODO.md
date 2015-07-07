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

## Misc. Engine Technology
 - [ ] Secure entry points of the engine by assertions (e.g. spring assertion framework?) - it is not wise to end up with a nullpointer exception deep in the guts of the engine and it would take hell of a time to debug this and trace the origin of the problem
 - [ ] Two characters cannot wear or wield the same item at once.
