import random

lista_palabras = ["programacion"]
palabras = str(random.choice(lista_palabras))
palabras = palabras.upper()
palabra_juego = []
for i in palabras:
    palabra_juego.append(i)
palabra_oculta = []
for i in palabra_juego:
    palabra_oculta.append(i.replace(i, "_"))
print(f"\n{palabra_oculta}")


def dime_letra():
    letra = input("\nDIME UNA LETRA: ")
    letra = letra.upper()
    if letra in palabra_juego:
        posiciones = [i for i, x in enumerate(palabra_juego) if x == letra]
        for j in posiciones:
            palabra_oculta[j] = letra
        print(palabra_oculta)
        return True
    else:
        print(f"\nLA {letra} ES INCORRECTA\n")
        print(palabra_oculta)
        return False


contador = 6
while contador != 0:
    print(f"\nTIENES {contador} VIDAS")
    if palabra_juego == palabra_oculta:
        print("\nHAS GANADO")
        break
    if not dime_letra():
        contador = contador - 1
        if contador == 0:
            print(f"\nLA PALABRA ERA {palabras}")
            print("\nHAS PERDIDO")
