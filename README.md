## Usage

mods: subscribe `AddCopyRightPageEvent`

kubejs: 
```
WeDoCopyRightEvents.addPage(event => {
  //这两个是同一个方法
  event.add("本例子由<忆然>制作", "本例子由<忆然>制作")
  event["add(net.minecraft.network.chat.Component[])"]("本例子由<忆然>制作", "本例子由<忆然>制作")

  //因为泛型擦除你只能写Component[]
  event["add(java.util.List)"]([Text.of("本例子由<忆然>制作"), Text.of("本例子由<忆然>制作")])

  event["add(net.minecraft.network.chat.Component[])"]("本例子由<忆然>制作")
  event["add(net.minecraft.network.chat.Component[])"](["本例子由<忆然>制作", "本例子由<忆然>制作"])

  //填材质路径 这个则是指的 kubejs/assets/kubejs/textures/block/example_block.png
  event.addImage("kubejs:textures/block/example_block.png", 16, 16)
})
```

## Supported Versions
[![](https://raw.githubusercontent.com/intergrav/devins-badges/refs/heads/v3/assets/compact/available/curseforge_vector.svg)](https://www.curseforge.com/minecraft/mc-mods/we-do-copyright)

[![](http://cf.way2muchnoise.eu/full_1254805_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/we-do-copyright)
[![](http://cf.way2muchnoise.eu/versions/Available%20for_1254805_full.svg)](https://www.curseforge.com/minecraft/mc-mods/we-do-copyright/files)

[![](https://raw.githubusercontent.com/intergrav/devins-badges/refs/heads/v3/assets/compact/available/modrinth_vector.svg)](https://modrinth.com/mod/we-do-copyright)

[![](https://modrinth.roughness.technology/full_Omqm1rvx_downloads.svg)](https://modrinth.com/mod/we-do-copyright)
[![](https://modrinth.roughness.technology/versions/Available%20for_Omqm1rvx_full.svg)](https://modrinth.com/mod/we-do-copyright/versions)

![](https://raw.githubusercontent.com/intergrav/devins-badges/refs/heads/v3/assets/compact/supported/forge_vector.svg)
![](https://raw.githubusercontent.com/intergrav/devins-badges/refs/heads/v3/assets/compact/supported/fabric_vector.svg)
![](https://raw.githubusercontent.com/Hyperbole-Devs/vectors/refs/heads/neoforge_badges/assets/compact/supported/neoforge_vector.svg)

- NeoForge-1.21.1
- Forge-1.20.1
- Forge-1.19.2
- Fabric-1.20.1

## Will Support Versions

- none

Other versions please open an issue to request.
