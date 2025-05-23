import csv
import os

csv_datei = "uebersetzungen.csv"
path = "src/main/resources"


with open(csv_datei, newline="", encoding="utf-8") as f:
    reader = csv.reader(f, delimiter=";")
    zeilen = list(reader)
    sprachen = zeilen[0][1:] #die erste Zeile der csv datei beinhaltet die kürzel der sprachen
    dateien = {}
    
    for sprache in sprachen:
        dateiname = f"{path}/messages_{sprache}.properties"
        with open(dateiname, "w", encoding="utf-8") as datei:
            
            for zeile in zeilen[1:]:
                if not zeile:  #leere Zeilen sollen übersprungen werden
                    continue
                schluessel = zeile[0]
                uebersetzung = zeile[sprachen.index(sprache)+1]
                datei.write(f"{schluessel}={uebersetzung}\n")
