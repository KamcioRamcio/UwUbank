# Maciej Code Review

## Co jest super  

1. **Łatwa konfiguracja**  
   - Dzięki użyciu Spring Boot, Gradle i Dockera, projekt łatwo się buduje i uruchamia.  
   - Konfiguracja Dockera dla PostgreSQL i plik `schema.sql` są dobrze przygotowane i działają bez problemu.  

2. **Ciekawe dane testowe**  
   - Przykładowe oddziały, jak "Straw Hat Pirates", sprawiają, że testowanie jest fajniejsze.  
   - Pola, takie jak `branch_address` (adres oddziału) i `phone_number` (numer telefonu), dodają realizmu do bazy danych.  

3. **Dobre konfiguracje**  
   - Plik `application.properties` jest uporządkowany i działa dla różnych środowisk.  
   - Ustawienia CORS są gotowe do integracji z frontendem.  

---

## Com można poprawić 

1. **Problemy z bezpieczeństwem**  
   - **Zakodowane dane logowania:** Nazwa użytkownika i hasło (`uwubank/uwubank`) są zapisane w kodzie, co jest niebezpieczne. Lepiej użyć zmiennych środowiskowych lub bezpiecznych sposobów ukrycia ich.  

2. **Brak testów**  
   - Projekt nie zawiera żadnych testów. Dodanie testów, np. dla przelewów lub sald kont, zwiększyłoby niezawodność.  
   - Brakuje walidacji danych wejściowych, np. numerów telefonów lub sprawdzania, czy saldo nie jest ujemne. Można to łatwo dodać przy użyciu adnotacji Springa, takich jak `@NotNull` lub `@Size`.  

3. **Poprawki w bazie danych**  
   - Plik `schema.sql` nie zawiera kluczy obcych ani indeksów, które są ważne dla organizacji danych i szybszego wyszukiwania.  
   - Warto dodać więcej komentarzy w kodzie, np. wyjaśnienie, dlaczego używane jest `spring.jpa.hibernate.ddl-auto=update`.  

## Dodatkowe pomysły  

1. **Automatyczne sprawdzanie kodu**  
  
2. **Dokumentacja API**  


3. **Konfiguracja CI/CD**  
  

---
 
