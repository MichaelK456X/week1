package com.example.soal

import java.util.Scanner

class Makanan(var nama: String, var deskripsi: String, var harga: Int)
class ItemPesanan(val makanan: Makanan, val jumlah: Int)
class Pesanan(val namaPelanggan: String, val daftarItem: ArrayList<ItemPesanan> = ArrayList())

fun main() {
    val input = Scanner(System.`in`)
    val daftarMenu = arrayListOf(
        Makanan("Nasi Goreng", "ada telur, sayur, dan juga krupuk", 15000),
        Makanan("Mie Goreng", "ada telur, sayur, dan ayam", 12000)
    )
    val daftarSemuaPesanan = ArrayList<Pesanan>()

    while (true) {
        println()
        println("ORDER SYSTEM")
        println("1. Make order")
        println("2. View Orders")
        println("3. View Menu")
        println("4. Add Menu")
        println("5. Edit Menu")
        println("6. Delete Menu")
        println("7. Exit")
        print("Pilih: ")

        val pilihan = try {
            input.nextLine().toInt()
        } catch (e: Exception) {
            continue
        }

        if (pilihan == 1) {
            if (daftarMenu.isEmpty()) {
                println("Menunya tidak ada"); continue
            }
            print("Nama Customer: ")
            val nomorPesananBaru = Pesanan(input.nextLine())
            while (true) {
                for (indeks in daftarMenu.indices) {
                    println("${indeks + 1}. ${daftarMenu[indeks].nama} - Rp${daftarMenu[indeks].harga}")
                }
                print("Pilih nomor menu (Kalau sudah selesai pesan pilih 0): ")
                val indeksMenuDipilih = try {
                    input.nextLine().toInt() - 1
                } catch (e: Exception) {
                    -1
                }
                if (indeksMenuDipilih == -1) break
                if (indeksMenuDipilih in daftarMenu.indices) {
                    print("Jumlah: ")
                    val jumlahBeli = try {
                        input.nextLine().toInt()
                    } catch (e: Exception) {
                        0
                    }
                    if (jumlahBeli > 0) {
                        nomorPesananBaru.daftarItem.add(
                            ItemPesanan(
                                daftarMenu[indeksMenuDipilih],
                                jumlahBeli
                            )
                        )
                    }
                }
            }
            if (nomorPesananBaru.daftarItem.isNotEmpty()) daftarSemuaPesanan.add(nomorPesananBaru)
        } else if (pilihan == 2) {
            for (pesanan in daftarSemuaPesanan) {
                println("\n-- ${pesanan.namaPelanggan}'s ORDER --")
                var totalHarga = 0.0
                for (indeks in pesanan.daftarItem.indices) {
                    val item = pesanan.daftarItem[indeks]
                    val subTotal = item.makanan.harga * item.jumlah
                    totalHarga += subTotal
                    println("${indeks + 1}. ${item.makanan.nama} x${item.jumlah} Rp$subTotal")
                }
                println("------------------------\n" +
                        "TOTAL Rp$totalHarga")
            }
        } else if (pilihan == 3) {
            for (indeks in daftarMenu.indices) {
                println("${indeks + 1}. ${daftarMenu[indeks].nama} (${daftarMenu[indeks].deskripsi}) - Rp${daftarMenu[indeks].harga}")
            }
        } else if (pilihan == 4) {
            print("Nama: ")
            val namaBaru = input.nextLine()
            print("Deskripsi: ")
            val deskripsiBaru = input.nextLine()
            print("Harga: ")
            val hargaBaru = try {
                input.nextLine().toInt()
            } catch (e: Exception) {
                0
            }
            daftarMenu.add(Makanan(namaBaru, deskripsiBaru, hargaBaru))
        } else if (pilihan == 5) {
            print("Nomor menu yg mau diedit: ")
            val indeksEdit = try {
                input.nextLine().toInt() - 1
            } catch (e: Exception) {
                -1
            }
            if (indeksEdit in daftarMenu.indices) {
                print("Nama baru: ")
                daftarMenu[indeksEdit].nama = input.nextLine()
                print("Deskripsi baru: ")
                daftarMenu[indeksEdit].deskripsi = input.nextLine()
                print("Harga baru: ")
                try {
                    daftarMenu[indeksEdit].harga = input.nextLine().toInt()
                } catch (e: Exception) {
                }
            }
        } else if (pilihan == 6) {
            print("Nomor menu yg mau dihapus: ")
            val indeksHapus = try {
                input.nextLine().toInt() - 1
            } catch (e: Exception) {
                -1
            }
            if (indeksHapus in daftarMenu.indices) daftarMenu.removeAt(indeksHapus)
        } else if (pilihan == 7) break
    }
}
