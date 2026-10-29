package com.example.soal

import java.util.Scanner
import java.util.Random

fun main() {
    val input = Scanner(System.`in`)
    val acak = Random()
    val daftarElemen = arrayOf("Fire", "Water", "Grass")

    println("Who is your name?")
    var namaPemain = input.nextLine()
    println("Good luck , $namaPemain! because you are going to need it")

    var maxHp = 50;
    var maxMp = 30;
    var hp = 50;
    var mp = 30
    var hpPotion = 5;
    var mpPotion = 5;
    var totalKill = 0;
    var lifesteal = 0
    var evolutionStatus = false
    var multiplierDamage = 1.0

    while (true) {
        println()
        println("What are you going to do?")
        println("1. View Stats")
        println("2. Enter battle")
        print("Pilih: ")
        val pilihanMenu = input.nextLine()

        if (pilihanMenu == "1") {
            while (true) {
                println()
                println("--- $namaPemain's Stats ---")
                println("HP: $hp/ $maxHp")
                println("Mana: $mp/ $maxMp")
                println("Kills you need to evolve: $totalKill/ 5")
                println("Mana Potions you have: $mpPotion")
                println("Health Potions you have: $hpPotion")
                println("-------------------")

                println("a. Drink Mana Potion")
                println("b. Drink Health Potion")
                println("c. Rename youself")
                println("d. Back")
                print("Pilih: ")
                val opsiStats = input.nextLine()

                if (opsiStats.equals("a", true) && mpPotion > 0) {
                    mp = Math.min(maxMp, mp + 15); mpPotion--
                } else if (opsiStats.equals("b", true) && hpPotion > 0) {
                    hp = Math.min(maxHp, hp + 25); hpPotion--
                } else if (opsiStats.equals("c", true)) {
                    print("New name: "); namaPemain = input.nextLine()
                } else if (opsiStats.equals("d", true)) break
            }
        } else if (pilihanMenu == "2") {
            val elemenMusuh = daftarElemen[acak.nextInt(3)]
            val namaMusuh = elemenMusuh + "mon"
            var hpMusuh = 30

            while (hpMusuh > 0 && hp > 0) {
                println()
                println("--- BATTLE ---")
                println(namaPemain)
                println("HP: $hp/ $maxHp")
                println("Mana: $mp/ $maxMp")
                println("HP Potions: $hpPotion")
                println("MP Potions: $mpPotion")
                println()
                println(namaMusuh)
                println("HP: $hpMusuh")
                println("Type: $elemenMusuh")
                println("--------------")

                println("a. Fire Attack")
                println("b. Water Attack")
                println("c. Grass Attack")
                println("d. Drink potion")
                println("e. Run")
                print("Pilih: ")
                val opsiBattle = input.nextLine()

                if (opsiBattle.equals("e", true)) {
                    println("You run away"); break
                }
                if (opsiBattle.equals("d", true)) {
                    println("1. HP Potion")
                    println("2. MP Potion")
                    print("Pilih: ")
                    val pilihanPotion = input.nextLine()
                    if (pilihanPotion == "1" && hpPotion > 0) {
                        hp = Math.min(maxHp, hp + 25); hpPotion--
                    } else if (pilihanPotion == "2" && mpPotion > 0) {
                        mp = Math.min(maxMp, mp + 15); mpPotion--
                    }
                    continue
                }

                val indeksSerangan =
                    if (opsiBattle.equals("a", true)) 0 else if (opsiBattle.equals(
                            "b",
                            true
                        )
                    ) 1 else if (opsiBattle.equals("c", true)) 2 else -1
                if (indeksSerangan == -1) continue

                if (mp < 10) {
                    println("Not enough mana"); continue
                }
                mp -= 10

                var multiplierElemen = 1.0
                if ((indeksSerangan == 0 && elemenMusuh == "Grass") || (indeksSerangan == 1 && elemenMusuh == "Fire") || (indeksSerangan == 2 && elemenMusuh == "Water")) {
                    multiplierElemen = 2.0
                }

                val totalDamage = (10 * multiplierDamage * multiplierElemen).toInt()
                hpMusuh -= totalDamage
                println("You deal $totalDamage damage")

                if (evolutionStatus) {
                    hp = Math.min(maxHp, hp + lifesteal)
                    println("Lifesteal success! you recover $lifesteal HP.")
                }

                if (hpMusuh > 0) {
                    hp -= 10
                    println("Enemy dealt 10 damage to you!")
                }
            }

            if (hp <= 0) {
                println("You die, Game restart")
                maxHp = 50; maxMp = 30; hp = 50; mp = 30; hpPotion = 5; mpPotion = 5; totalKill =
                    0; lifesteal = 0; evolutionStatus = false; multiplierDamage = 1.0
            } else if (hpMusuh <= 0) {
                println("You defeated the enemy")
                totalKill++

                if (evolutionStatus) {
                    lifesteal++
                }

                if (!evolutionStatus && totalKill >= 5) {
                    evolutionStatus = true
                    multiplierDamage = 1.5
                    maxHp = (50 * 1.5).toInt()
                    maxMp = (30 * 1.5).toInt()
                    hp = maxHp
                    mp = maxMp
                    lifesteal = 1
                    println("Congrats! You evolved into a Strong Wizard")
                }
            }
        }
    }
}
