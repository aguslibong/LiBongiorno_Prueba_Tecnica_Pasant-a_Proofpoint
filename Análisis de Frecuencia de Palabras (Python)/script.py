import sys
import re
from collections import Counter

def analizar_frecuencia(archivo):
    try:
        with open(archivo, 'r', encoding='utf-8') as f:
            texto = f.read().lower()  

        palabras = re.findall(r'\b\w+\b', texto)  
        frecuencia = Counter(palabras)  

        top_10 = frecuencia.most_common(10)  
        for palabra, conteo in top_10:
            print(f" {palabra}: {conteo} ")

    except FileNotFoundError:
        print(f"Error: No se encontró el archivo '{archivo}'.")




if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Uso: python script.py <archivo.txt>")
    else:
        analizar_frecuencia(sys.argv[1])
        print("Creado por Agustin Li Bongiorno")
