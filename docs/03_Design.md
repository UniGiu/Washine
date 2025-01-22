
**Software Architecture**

**Viste arcitettoniche**  

**Architettura**

Abbiamo sviluppato il progetto seguendo un'architettua a tre livelli, in cui ogni livello utilizza le funzionalità del livello sottostante, garantendomodularità e separazione.
I tre livelli che costituiscono il nostro sistema sono:
- **Database:**  
Livello inferiore dell'architettura, responsabile della gestione e memorizzazione dei dati. È stato realizzato con il supporto di JOOQ.
- **Logico (Core):**  
Livello intermedio, responsabile di gestire la logica applicativa del sistema e rappresenta il punto di collegamento tra il database e l'interfaccia grafica.
- **GUI:**  
Livello superiore dell'architettura che rappresenta il punto di interazione con l'utente. Realizzato utilizzando il framework Vaadin.

**Software Design**  
**Descrizione Design**  
**Calcolo Complessità**  
**Misurazione Codice**  
**Design Pattern**  

