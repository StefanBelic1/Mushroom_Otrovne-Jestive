# Mushroom

Mushroom je Android aplikacija za prikaz, dodavanje i brisanje gljiva, podijeljenih u dvije kategorije: jestive i otrovne. Podaci o gljivama pohranjuju se u Firebase Firestore bazu podataka.

## Tehnologije
- **Kotlin** - glavni programski jezik
- **Jetpack Compose** - moderni UI framework
- **Dagger Hilt** - upravljanje ovisnostima
- **Firebase Firestore** - baza podataka u oblaku
- **Navigation Component** - upravljanje navigacijom unutar aplikacije

## Značajke
- Pregled gljiva podijeljenih u dvije kategorije: jestive i otrovne
- Dodavanje novih gljiva u bazu podataka
- Brisanje gljiva iz baze podataka
- Navigacija između ekrana

## Struktura projekta
- **MainActivity.kt** - Glavna aktivnost koja pokreće aplikaciju i učitava `MushroomApp`
- **MushroomApp.kt** - Aplikacijska klasa koja omogućuje Hilt integraciju
- **Navigation.kt** - Definira navigaciju između različitih ekrana aplikacije
- **Routes.kt** - Sadrži definicije ruta korištenih u navigaciji

## Instalacija i pokretanje
1. Klonirajte repozitorij:
   ```sh
   git clone https://github.com/your-repo/mushroom-app.git
   ```
2. Otvorite projekt u Android Studiju.
3. Postavite Firebase Firestore i preuzmite `google-services.json` u `app/` direktorij.
4. Pokrenite aplikaciju na emulatoru ili fizičkom uređaju.

## Autor
Stefan Belić


