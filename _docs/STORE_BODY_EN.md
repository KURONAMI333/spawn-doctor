You built the mob farm exactly like the tutorial and nothing spawns. `/spawndoctor` tells you why, in plain words — not a flame graph, not an overlay.

Spawn-overlay mods only show light level. None of them explain why a correctly-built farm produces nothing: hostiles loaded far away eating the spawn budget, an AFK spot too close, or simulation distance. Stand where you expect spawns and run the command.

```
Spawn Doctor — why your mob farm is dead:
42 hostile mobs are loaded FAR from you vs only 3 nearby. The far ones eat
the same spawn budget your farm needs — light up caves / clear them.
 ┃ Mobs spawn 24-128 blocks from a player: AFK at least 24 blocks away…
 ┃ Server simulation distance: 10 chunks — the farm must be within that…
```

It calls out the dead-certain causes (Peaceful difficulty, `doMobSpawning` off), surfaces the usual silent killer (far-loaded hostiles eating the cap), and always states the universal spawn rules (the 24–128 ring, simulation distance). The mob-cap formula is version-sensitive, so it never claims an exact "cap X/Y" — a wrong number is worse than none. Open to all players, 30-second cooldown (ops bypass).

Read-only and on-demand: no mixin, no config, no blocks or items.

Bugs and questions: comment on the CurseForge page, or DM @kuronami333 on X.

All Rights Reserved. Modpack inclusion is allowed without permission or credit. Source: https://github.com/KURONAMI333/spawn-doctor
