import csv
import math
a={"longitude":[],"latitude":[],"boutique":[]}
with open('free_shop.csv', newline='') as csvfile:
    reader = csv.DictReader(csvfile)
    for row in reader:
        print(row)
        #print(row['longitude'], row['latitude'])
        s=row["longitude;latitude;shopDescription;sunusng;ipom;weiwei"]
        #print(s)
        x=s.find(";")
        #print(s.find(";"))
        #print(s[0:x])
        #print(s[x+1:])
        z=s[x+1:]
        y=z.find(";")
        #print(z[0:y])
        a["longitude"].append(s[0:x])
        a["latitude"].append(z[0:y])
        w=z[y+1:]
        h=w.find(";")
        print(w[0:h])
        #print(w)
        a["boutique"].append(w[0:h])
p=len(a["longitude"])
#print(p)

lat=48.90747
longi=2.34104
d1=(float(a["longitude"][1])-longi)*(float(a["longitude"][1])-longi)+ (float(a["latitude"][1])-lat)*(float(a["latitude"][1])-lat)
for i in range(p):
    d=(float(a["longitude"][i])-longi)*(float(a["longitude"][i])-longi)+(float(a["latitude"][i])-lat)*(float(a["latitude"][i])-lat)
    if (d<d1):
        imin=i
        d1=d
    #print(a["longitude"][i], " ", a["latitude"][i], " ", a["boutique"][i])
print("La boutique la plus proche est", a["boutique"][imin])







#print(a["longitude"]," et ", a["latitude"])




#print(row)
