# Spawn Doctor

> You built the mob farm exactly like the tutorial and nothing spawns. `/spawndoctor` tells you why — in plain words.

"Built it like the tutorial, nothing spawns" is one of the most repeated cries on r/Minecraft, r/feedthebeast and Aternos. Existing spawn overlays only show *light level* — none explain *why a correctly-built farm produces nothing* (cap theft from far-loaded mobs, AFK ring, sim distance). That's the gap this fills.

- 🩺 One command, plain-language diagnosis — not a flame graph, not an overlay
- 🎯 Calls out the dead-certain causes: Peaceful difficulty, `doMobSpawning` off
- 🕳️ Surfaces the #1 silent killer: hostiles loaded far away eating the spawn budget your farm needs
- 📏 Always-shown spawn rules: the 24–128 ring and the server's simulation distance

## What it does / Usage

Stand where you expect spawns, run `/spawndoctor`:

```
Spawn Doctor — why your mob farm is dead:
42 hostile mobs are loaded FAR from you vs only 3 nearby. The far ones eat
the same spawn budget your farm needs — light up caves / clear them.
 ┃ Mobs spawn 24-128 blocks from a player: AFK at least 24 blocks away…
 ┃ Server simulation distance: 10 chunks — the farm must be within that…
```

Open to all players; 30s cooldown (ops bypass).

## Supported loaders / versions

| Minecraft | NeoForge | Forge | Fabric |
|---|:---:|:---:|:---:|
| 1.21.1 | ✅ | planned | planned |

Forge / Fabric / 1.20.1 ports planned; this release is NeoForge 1.21.1.

## Dependencies

None.

## Compatibility & scope

Read-only, on-demand, server-side. No mixin, no config, no blocks/items — can't conflict with other mods.

## Known limitations

The mob-cap formula is version-sensitive and easy to get wrong, so Spawn Doctor **never claims an exact "cap X/Y"** — a wrong number is worse than none. It reports the certain, explanatory facts (the cap-theft framing is true regardless of the exact cap) plus the universal spawn rules.

## Install

1. Install NeoForge for Minecraft 1.21.1.
2. Drop `spawndoctor-0.1.0.jar` into `mods/`. Server-side.

- Minecraft 1.21.1 · NeoForge · JDK 21

## Languages

Output localized in 9 languages (machine-baseline; native-speaker PRs welcome).

## License

MIT — modpack inclusion welcome, no credit required.

Author: KURONAMI
