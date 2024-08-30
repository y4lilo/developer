"""Escribe una función llamada contar_primos() que requiera un solo argumento numérico.
Esta función va a mostrar en pantalla todos los números primos existentes en el rango que va desde cero hasta ese
número incluido, y va a devolver la cantidad de números primos que encontró."""


def contar_primos(numero):
    contador = 2
    lista_primos = []
    while contador <= numero:
        for i in range(2, contador):
            if contador % i == 0:
                contador += 1
                break
        else:
            lista_primos.append(contador)
            contador += 1
    print(lista_primos)
    return len(lista_primos)


print(contar_primos(17))
