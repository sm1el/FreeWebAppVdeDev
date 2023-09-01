import csv
import math
import json
from urllib.request import urlopen
import folium
import webbrowser



def Maposition():
    local=[]
    urlopen("http://ipinfo.io/json")
    data = json.load(urlopen("http://ipinfo.io/json"))
    local.append(data['loc'].split(',')[0])
    local.append(data['loc'].split(',')[1])
    return local

localiser=Maposition()
longi=float(localiser[0])
lat=float(localiser[1])
#print(longi)
#print(lat)
tel=input("Le téléphone que vous voulez achetez: ")
#tel="sunusng"
a={"longitude":[],"latitude":[],"boutique":[], "sunusng":[], "ipom":[], "weiwei":[]}
with open('free_shop.csv', newline='') as csvfile:
    reader = csv.DictReader(csvfile)
    for row in reader:
        #print(row)
        #print(row['longitude'], row['latitude'])
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
        #print(w[0:h])
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




    #print(a["longitude"][i], " ", a["latitude"][i], " ", a["boutique"][i])
if tel=="ipom":
    print("La boutique la plus proche est", a["boutique"][imin])
    print("Il reste ",a["ipom"][imin], " disponibles pour la marque ",tel)
else:
    if tel=="sunusng":
        print("La boutique la plus proche est", a["boutique"][imin])
        print("Il reste ", a["sunusng"][imin], " disponibles pour la marque ", tel)
#print("Telephone de marque", tel, "est disponible, et il en reste", a["ipom"][i], " disponible")
    else:
        if tel=="weiwei":
            print("La boutique la plus proche est", a["boutique"][imin])
            print("Il reste ", a["weiwei"][imin], " disponibles pour la marque ", tel)
        else:
            print("marque telephone non disponible")




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


LesBoutiques = folium.Map(location=[lat, longi], tiles='OpenStreetMap', zoom_start=12.5)
p=len(a["boutique"])
for i in range(p):
    folium.Marker(location=[a["latitude"][i], a["longitude"][i]], popup =a["boutique"][i] ).add_to(LesBoutiques)

LesBoutiques.save('LesBoutiques.html')
#webbrowser.open('LeBoutique.html')

"""
carte= folium.Map(location=[a["longitude"][imin], a["latitude"][imin]],zoom_start=14)

folium.Marker([a["longitude"][imin], a["latitude"][imin]],popup="Boutique plus proche",icon=folium.Icon(color='green')).add_to(carte)
folium.Marker([longi, lat],popup="Ta position",icon=folium.Icon(color='green')).add_to(carte)
trail_coordinates = [
    (longi, lat),
    (a["longitude"][imin], a["latitude"][imin]),
  ]
folium.PolyLine(trail_coordinates, tooltip="Coast").add_to(carte)
carte.save('Carte2.html')
webbrowser.open('Carte2.html')
"""



""" 
map = folium.Map(location=[lat, longi], tiles='OpenStreetMap', zoom_start=10)
coords = [float(a["longitude"][imin]), float(a["latitude"][imin])]
folium.Marker(location=[lat, longi], popup = "Votre Adresse").add_to(map)
folium.Marker(location=[a["latitude"][imin], a["longitude"][imin]], popup = a["boutique"][imin]).add_to(map)
trail_coordinates = [
    (lat, longi),
    (a["latitude"][imin], a["longitude"][imin]),
  ]
folium.PolyLine(trail_coordinates, tooltip="Coast").add_to(map)
map.save('map.html')
"""

