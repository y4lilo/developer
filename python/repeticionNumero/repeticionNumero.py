"""Escribe una función que requiera una cantidad indefinida de argumentos.
Lo que hará esta función es devolver True si en algún momento se ha ingresado
al numero cero repetido dos veces consecutivas."""


def ejercicio3(*args):
    lista = []
    for i in range(len(args) - 1):
        if args[i] == 0 and args[i] == args[i + 1]:
            return True
    return False


print(ejercicio3(2, 3, 0, 0, 3, 4))
