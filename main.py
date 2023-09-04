import csv
"""Importation du fichier csv"""
import math
import json
from urllib.request import urlopen
"""Bibliothèque pour me permet de récupérer des données à partir d'une URLS"""
import folium
"""Bibliothèque qui me permet de représenter une postion sur une carte"""
import webbrowser
from geopy.geocoders import Nominatim

""" Fonction qui permet de trouver la position GPS"""
def Maposition():
    local=[]
    urlopen("http://ipinfo.io/json")
    data = json.load(urlopen("http://ipinfo.io/json"))
    local.append(data['loc'].split(',')[0])
    local.append(data['loc'].split(',')[1])
    return local

localiser=Maposition() #chargement des données dans une structure de données, dictionnaire
lat=float(localiser[0]) #Récupération de la latitude
longi=float(localiser[1]) #Récupération de la longitude

tel=input("Le téléphone que vous voulez achetez: ") #Demander la marque de téléphone que le client veut


"""Création d'un distionnaire pour arranger les données CSV"""
a={"longitude":[],"latitude":[],"boutique":[], "sunusng":[], "ipom":[], "weiwei":[]}
with open('free_shop.csv', newline='') as csvfile:
    reader = csv.DictReader(csvfile)
    for row in reader:
        s=row["longitude;latitude;shopDescription;sunusng;ipom;weiwei"]
        s2=s.split(";")
        s1=row["longitude;latitude;shopDescription;sunusng;ipom;weiwei"].split(";")
        #print(len(s1))
        a["longitude"].append(s1[0])
        a["latitude"].append(s1[1])
        a["boutique"].append(s1[2])
        if len(s1)==3:
            nep=row[None][0].split(";")
            #print(nep,len(nep))
            a["sunusng"].append(nep[1])
            p=len(a["boutique"])
            a["boutique"][p-1]=a["boutique"][p-1]#+nep[0]
            a["ipom"].append(nep[2])
            a["weiwei"].append(nep[3])
        else:
            a["sunusng"].append(s1[3])
            a["ipom"].append(s1[4])
            a["weiwei"].append(s1[5])
        """ 
        #print(s)
        x=s.find(";")
        #print(s.find(";"), beg)
        #print(s[0:x])
        #print(s[x+1:])
        """
        """ 
        z=s[x+1:]
        y=z.find(";")
        #print(z[0:y])
        a["longitude"].append(s[0:x])
        a["latitude"].append(z[0:y])
        w=z[y+1:]
        h=w.find(";")
        #print(w[0:h])       #Cette partie, C'était une méthode, des manipulation pour arranger les données
        #print(w)
        a["boutique"].append(w[0:h])
        g=w[h+1:]
        disp=g.find(";")
        #a["Dispo"].append(g[0:disp])
        """
p=len(a["longitude"])
#print(p)
tel="ipom"
lat=48.90747
longi=2.34104




"""Cette partie c'est pour trouver la boutique la plus proche et récupérer l'adresse,
Y compris de vérifier s'il y a un stock disponible pour la marque"""
d1=(float(a["longitude"][1])-longi)*(float(a["longitude"][1])-longi)+ (float(a["latitude"][1])-lat)*(float(a["latitude"][1])-lat)
for i in range(p):
    d=(float(a["longitude"][i])-longi)*(float(a["longitude"][i])-longi)+(float(a["latitude"][i])-lat)*(float(a["latitude"][i])-lat)
    if (d<d1):
        if tel=="sunusng":
            if int(a["sunusng"][i])>=1:
                imin=i
                d1=d

        if tel=="ipom":
            if int(a["ipom"][i])>=1:
                imin=i
                d1=d

        if tel=="weiwei":
            if int(a["weiwei"][i])>=1:
                imin=i
                d1=d




"""Cette partie vérifie si la maque est disponible"""
if tel=="ipom":
    print("La boutique la plus proche est", a["boutique"][imin])
    print("Il reste ",a["ipom"][imin], " disponibles pour la marque ",tel)
    poss=1
else:
    if tel=="sunusng":
        print("La boutique la plus proche est", a["boutique"][imin])
        print("Il reste ", a["sunusng"][imin], " disponibles pour la marque ", tel)
        poss = 1
    else:
        if tel=="weiwei":
            print("La boutique la plus proche est", a["boutique"][imin])
            print("Il reste ", a["weiwei"][imin], " disponibles pour la marque ", tel)
            poss = 1
        else:
            print("marque telephone non disponible")
            poss = 0


"""Si le téléphone existe alors je représente la boutique la plus proche et la position du client"""
if poss==1:
    maCarteBoutique = folium.Map(location=[lat, longi], tiles='OpenStreetMap', zoom_start=12.5)
    coords = [float(a["longitude"][imin]), float(a["latitude"][imin])]
    folium.Marker(location=[lat, longi], popup = "Votre Adresse").add_to(maCarteBoutique)
    folium.Marker(location=[a["latitude"][imin], a["longitude"][imin]], popup = a["boutique"][imin]).add_to(maCarteBoutique)
    trail_coordinates = [
        (lat, longi),
        (a["latitude"][imin], a["longitude"][imin]),
    ]
    folium.PolyLine(trail_coordinates, tooltip="Coast").add_to(maCarteBoutique)
    maCarteBoutique.save('maCarteBoutique.html')
    webbrowser.open('maCarteBoutique.html')



"""Représentation de toutes les boutiques de France"""
LesBoutiques = folium.Map(location=[lat, longi], tiles='OpenStreetMap', zoom_start=12.5)
p=len(a["boutique"])
for i in range(p):
    folium.Marker(location=[a["latitude"][i], a["longitude"][i]], popup =a["boutique"][i] ).add_to(LesBoutiques)

LesBoutiques.save('LesBoutiques.html')
#webbrowser.open('LeBoutique.html')









