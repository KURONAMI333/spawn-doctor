# Spawn Doctor

> You built the mob farm exactly like the tutorial and nothing spawns. `/spawndoctor` tells you why — in plain words.

## What it does

Stand where you expect spawns, run `/spawndoctor`, and get a plain-language diagnosis from **certain vanilla facts only**:

```
Spawn Doctor — why your mob farm is dead:
42 hostile mobs are loaded FAR from you vs only 3 nearby. The far ones eat
the same spawn budget your farm needs — light up caves / clear them. This
is the #1 reason a correctly-built farm is dead.
 ┃ Mobs spawn 24-128 blocks from a player: AFK at least 24 blocks away,
   and the platform must be within 128 blocks of you.
 ┃ Server simulation distance: 10 chunks — the farm must be within that
   of your AFK spot to tick.
```

It also calls out the dead-certain causes when present: **Peaceful difficulty** and **`doMobSpawning` off**.

## Why

"Built it like the tutorial, nothing spawns" is one of the most repeated cries on r/Minecraft, r/feedthebeast and Aternos. Existing spawn overlays only show *light level* — none explain *why a correctly-built farm produces nothing* (cap theft from far-loaded mobs, AFK ring, sim distance). That's the gap this fills.

## Honest scope

The mob-cap formula is version-sensitive and easy to get wrong, so Spawn Doctor **never claims an exact "cap X/Y"** — a wrong number is worse than none. It surfaces the certain, explanatory facts instead (the cap-theft framing is true regardless of the exact cap) plus the universal spawn rules. Open to all players, 30s cooldown (ops bypass).

## Install

Drop `spawndoctor-<version>.jar` into `mods/`. Server-side. No dependencies.

- Minecraft 1.21.1 · NeoForge · JDK 21

Read-only, on-demand. No mixin, no config, no blocks/items. 9 languages (machine-baseline; native PRs welcome).

## License

MIT — modpack inclusion welcome, no credit required.

Author: KURONAMI
