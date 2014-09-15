Jeg valgte å gjøre denne oppgaven litt grundig for min egen del, med generics, iterators, comparable, osv.

GUI kan kjøres i flere instanser, for å emulere flere kundebehandlere, du kan også emulere flere kundebehandlere bare ved å skifte navn på toppen.
Det lagres en .ser fil som gjør at flere instanser kan vise samme informasjon.

Oppgave 11 valgte jeg å gjøre istedenfor å forklare, så bruker får nå estimat på hvor lenge han/hun må vente basert på tidligere ventetider.

Klasser:
    Customer:       Inneholder informasjon for kunder
    CustomerStatus: Enum for status på kunder
    DoubleList:     Listen som inneholder elementene i form av DoubleNode
    DoubleNode:     Elementene som er i listen
    GUI:            Inneholder koden for GUI programmet og logikken
    Oppgave:        Inneholder main metode og kjører de forskjellige deler av programmet
    Tests:          Inneholder statiske metoder som kjører tester og printer ut resultatene (Gjør dette både med int og String, ved bruk av generics)