
# Architettura e Design
## Indice

## 1. Software Architecture
### Architettura

Abbiamo sviluppato il progetto seguendo un'architettua a tre livelli, in cui ogni livello utilizza le funzionalità del livello sottostante, garantendo modularità e separazione.  Questo dovrebbe rispondere ai requisiti non funzionali di adattabilità, manutenibilità e favorire analizzabilità. 
I tre livelli che costituiscono il nostro sistema sono:
- **Database:**  
Livello inferiore dell'architettura, responsabile della gestione e memorizzazione dei dati. È stato realizzato con il supporto di JOOQ.
- **Logico (Core):**  
Livello intermedio, responsabile di gestire la logica applicativa del sistema e rappresenta il punto di collegamento tra il database e l'interfaccia grafica.
- **UI:**  
Livello superiore dell'architettura che rappresenta il punto di interazione con l'utente. Realizzato utilizzando il framework Vaadin.

Abbiamo anche deciso di seguire il pattern architetturale MVC nello sviluppo livelli e abbiamo quindi individuato 4 componenti principali:


- **Un componente di presentazione:**
 Questo componente appartiene al livello UI e abbiamo deciso che trattandosi di applicazione web interagisca con gli utenti attraverso il protocollo HTTP e con i livelli sottostanti esclusivamente attraverso interfacce astratte in modo da essere il più possibile modulare e disaccoppiato dal livello inferiore. 
Nell'implementazione attuale abbiamo utilizzato il framework Vaadin (Vaadin Flow) in modo da poter implementare un'interfaccia grafica che risponda ai principali requisiti requisiti di usabilità e di interfaccia utente previsti.
In virtù della sua modularità questo componente in alternativa potrebbe anche essere un sito web sviluppato con un altro framework, magari più accessibile per  categorie di utenti, o un'API a cui potrebbero connettersi appplicazioni di terze parti o dispositivi intelligenti, ad esempio un cesto della biancheria IOT. 

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/immagini/vaadin-application-architecture.PNG)


- **Un componente controller**
Anche questo è un componente, che appartiene al livello Logico (Core), prevede la modularità sia per rispondere ai requisiti i di adattabilità che di manutenibilità. Oltre ad essere fortemente disaccoppiato dai livelli superiore e inferiore anche al suo interno è composto da componenti modulari cui è affidata la responsabilità di una parte del servizio. Abbiamo previsto che per la stessa responsabilità sia possibile avere componenti diversi che in diverse combinazioni possano fornire gli stessi servizi al livello superiore in base al tipo di componente di presentazione utilizzato. Ciò oltre a rispondere al requisito di adattabilità in fase di sviluppo di rende possibile fornire transitoriamente dei servizi fittizi al componente di presentazione.
Questi componenti interni sono senza memoria, nel senso che la responsabilità della coerenza di stato del sistema gestito dal software è delegata al livello Database, non esiste uno stato intermedio: dato un particolare input dal livello UI questi componenti ne controlleranno la validità, ne cureranno l'elaborazione, genereranno un nuovo stato da portare al livello inferiore e si prenderanno carico di fornire una risposta appropriata al livello UI. Dopo questo nulla di ciò che è stato viene tenuto e il componente è pronto per rispondere a un nuovo input come se fosse il primo.
Il componente controller comunica anche con il livello Database, sempre tramite un'interfaccia astratta.


- **Un componente modello logico**
Questo componente del livello Logico (Core) è quello che permette di rappresentare gli oggetti della business logic che vengono utilizzati dal componente controller. Viene poi esposto dal controller al livello Ui esclusivamente tramite le interfacce degli oggetti. Si tratta di un componente passivo che modella e stuttura l'informazione del dominio dell'applicazione e ne permette la manipolazione.


- **Un componente modello fisico**-Anche questo è un componente che può essere sostituito, grazie a dei servizi di interrogazione modulari. Rispetto al componente di presentazione in questo caso il sistema è meno complesso perché più stabile e meno potenzialmente esposto alla varietà dell'ambiente esterno. I requisiti di manutenibilità adattabiltià e di portabilità hanno comunque pesato nella scelta della modularità. Il database che abbiamo utilizzato è SQLLite
e il servizio di interrogazione dell'applicazione vi accede tramite la libreria di mapping JOOQ. 

Nell'immagine è possibile vedere i tre livelli e i componenti che li realizzano secondo MVC.

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/immagini/ComponentiWashineBig.SVG)

 decisioni progettuali da notare:
 
Implementazione autenticazione

|Elemento   |Descrizione   |
|---|---|
| Problema  | Per fornire il servizio è necessario identificare l'utente che compie le operazioni  |
| Decisione  | Implementare un sistema di autenticazione minimale e indipendente dalla scelta di componente di presentazione, lo stato dell'autenticazione è memorizzato a livello di sessione ||
| Stato  | Approvato ||
| Presupposti  | Il sistema è accessibile da web e utilizza HTTP/HTTPS |   |
| Alternative  |  Vaadin ha un sistema di autenticazione utente raffinato che permette di gestire aspetti come il routing e privilegi ||
| Motivazione  | Un sistema implementato utilizzando le variabili di sessione del componente e che salvi le credenziali è indipendente dal framework utilizzato per la presentazione. Inoltre è più semplice integrare la gestione degli utenti anche a livello di business logic in quanto le relazioni tra gli utenti sono parte del dominio dell'applicazione e facilitare e organizzare la loro interazione è tra i requisiti ||
| Implicazioni  | Vanno implementate funzionalità già fornite da Vaadin  ||
| Appunti  | /  ||

bb
### Viste architettoniche

## 2. Software Design
### Descrizione Design
### Calcolo Complessità
### Misurazione Codice
### Design Pattern 

