
# Piano di progetto
## Indice

1. [Introduzione](#1-introduzione)
2. [Modello di processo](#2-modello-di-processo)
3. [Organizzazione del progetto](#3-organizzazione-del-progetto)
4. [Standard, linee guida, procedure](#4-standard-linee-guida-procedure)
5. [Attività di gestione](#5-attività-di-gestione)
6. [Rischi](#6-rischi)
7. [Personale](#7-personale)
8. [Metodi e tecniche](#8-metodi-e-tecniche)
9. [Garanzia di qualità](#9-garanzia-di-qualità)
10. [Pacchetti di lavoro (workpackages) ](#10-pacchetti-di-lavoro-workpackages )
11. [Risorse](#11-risorse)
12. [Budget e programma](#12-budget-e-programma)
13. [Cambiamenti](#13-cambiamenti)
14. [Consegna](#14-consegna)

## Piano

###  1. Introduzione

[Indice ^](#indice)
> Nell'introduzione al piano di progetto, vengono forniti il background e la storia del progetto, insieme ai suoi obiettivi, i risultati del progetto, i nomi delle persone responsabili e una sintesi del progetto.

#### Background, storia, e obiettivi

Il progetto nasce come progetto per l'esame universitario di Ingegneria del Software.  
Rispetto ad un progetto commerciale questo ha quindi caratteristiche diverse per contesto e durata: si tratta di 3/4 studenti che in un paio di mesi devono documentare e sviluppare un software con strumenti previsti a priori seguendo metodologie e pratiche per loro nuove. L'obiettivo è sperimentare insieme quanto visto e studiato durante il corso e dimostrare di averlo ben compreso e messo in pratica con efficacia.

#### Risultati del progetto

Ci sono riusciti? Questa parte è ancora da scrivere, speriamo di sì!

#### Persone coinvolte

-   Suardi Paolo, mat 1087357
-   Tilli Giuseppe, mat 1086587
-   Toffalori Chiara, mat 1080710

#### Sintesi del progetto

**Washine** è un applicazione web open source che permette di organizzare e favorire la condivisione tra più utenti di un lavaggio di una lavatrice domestica o a gettoni. 
Il software si rivolge a piccole comunità di utenti accumunati dalla prossimità geografica e un legame di reciproca conoscenza e fiducia (amici, condomini, colleghi di lavoro, parrocchiani, frequentatori di un bar, studenti fuori sede, abitanti di un quartiere...).  Una seconda fase, anche in virtù della natura open source del progetto, potrebbe portare ad allargare la base di utenza e prevedere configurazioni del prodotto per finalità commerciali.  

Idealmente l'applicazione si propone di ridurre i consumi energetici globali relativi ai lavaggi e alla produzione di macchine lavatrici e di rafforzare i legami di comunità.

Nello specifico attraverso un database e un'interfaccia web viene fornita agli utenti la possibilità di programmare un lavaggio di indumenti specificandone le caratteristiche (tipologia indumenti, colori, peso disponibile, tipo di detergente, asciugatrice, quota spese, meeting point, utenti abilitati a visualizzare l'offerta ecc...) oppure di prenotare un lavaggio tra quelli disponibili (per le comunità di cui si fa parte, per essere inclusi da chi offre il lavaggio o per geolocalizzazione approssimata) specificando la quota di indumenti (peso) che si vuole prenotare. *Ci dovrebbe essere poi un utente Amministratore che può monitorare l'attività degli utenti e intervenire in casi particolari grazie a dei privilegi, se riusciamo a fare in modo che sia un ruolo inutile è meglio*.

###  2. Modello di processo

[Indice ^](#indice)
> Nel Capitolo 1 è stato introdotto un semplice modello del ciclo di vita
per discutere le varie attività da affrontare in un progetto di sviluppo software. Esistono
molte varianti di questo modello di processo, alcune delle quali sono discusse nel Capitolo 3.
Per ogni progetto si deve decidere l'esatto modello di processo da seguire: quali attività
intraprendere, quali pietre miliari possono essere identificate, come si accerta se tali pietre
miliari vengono raggiunte e quali sono i percorsi critici. Diversi tipi di progetti hanno
caratteristiche diverse e quindi richiedono modelli di processo diversi.
Ad esempio, il project plan potrebbe stabilire che si utilizzerà un processo agile SCRUM.

###  3. Organizzazione del progetto 

[Indice ^](#indice)
> Il rapporto del progetto con altri enti e l'organizzazione del
progetto stesso sono trattati in questa rubrica. Il progetto avrà una relazione con
l'organizzazione utente, l'organizzazione madre ed eventualmente con altre organizzazioni. I
potenziali utenti saranno, di volta in volta, coinvolti nel progetto. Il piano del progetto
deve indicare quali informazioni, servizi, risorse e strutture devono essere fornite dagli
utenti e quando devono essere forniti.
All'interno del team di progetto possono essere identificati vari ruoli: project manager,
tester, programmatore, analista, ecc. È necessario delineare chiaramente questi ruoli e
identificare le responsabilità di ciascuno di essi. Se ci sono lacune nelle conoscenze
richieste per ricoprire uno di questi ruoli, è necessario identificare la formazione e
l'istruzione necessarie per colmare queste lacune. Diverse forme di organizzazione del
team sono discusse nel Capitolo 5 .

## Team

Anche se la scelta del modello di processo RAD prevede un team SWAT questo nel nostro caso non è di fatto propriamente praticabile poiché i membri del team non sono particolarmente skilled con gli advanced tools e sia le persone che i tools sono dati a priori e non sostituibili con altri. Sicuramente ci verrà spontaneo fare brevi e frequenti riunioni come i team agili e SWAT, siamo nella stessa aula spesso tra una lezione e l'altra. Non mancheranno le occasioni di fare brainstorming, soprattutto tramite messaggistica online. 

Come tutti i principianti non ci verrà difficile sentirci orgogliosi del nostro lavoro come i veri team SWAT, ma tra i modelli organizzativi presi in considerazione quello che sembra più adatto alla nostra situazione sembra essere quello a matrice. Abbiamo bisogno di essere molto efficaci ed efficienti e questo tipo di organizzazione ci permette di dividerci per diverse "specialità" e lavorare in part-time. Tutti noi nella nostra attività di studenti siamo impegnati in altri progetti per i quali non stiamo collaborando e che in un certo senso competono con questo per l'uso del nostro tempo. Come per molti contesti in cui viene utilizzata l'organizzazione a matrice la realizzazione di software in sé non costituisce la nostra attività principale di studenti di ingegneria (ne abbiamo prodotto relativamente poco fin ora). Anche nello studio dell'ingegneria del software lo sviluppo di questa applicazione non è che una parte dell'esame, forse anche la meno importante. 

Un altro argomento a favore dell'organizzazione a matrice è la considerazione che ciascuna unità base "specializzata" persegue l'obiettivo di lungo periodo dell'ampliamento delle proprie conoscenze e competenze - il vero scopo del progetto e dei nostri studi - e che il project management (qui attività colletiva) ha l'obiettivo di finire il progetto in tempo e permettere di superare a tutti l'esame presto e bene. La specializzazione delle unità base in questo caso è in divenire.  

Probabilmente in molti casi ci troveremo in anche una forma molto blanda del modello programmatore capo poichè siamo veramente pochi: alcune unità base saranno formate dalle stesse persone, ma uno sarà responsabile di quella parte di codice, un altro potrebbe assisterlo (o essere suo pari) e un terzo che farà da revisore e si prenderà carico di svolgere anche il ruolo del bibliotecario. Questo sarà utile che tutti siano più o meno al corrente di più o meno tutto.

###  4. Standard, linee guida, procedure 

[Indice ^](#indice)
> I progetti software sono grandi progetti. Di solito, molte
persone sono coinvolte. Occorre quindi una forte disciplina di lavoro, in cui ogni persona
coinvolta segua gli standard, le linee guida e le procedure concordate. Oltre ad essere
dichiarati sulla carta, molti di questi possono essere supportati o applicati da strumenti. Di
estrema importanza sono gli accordi chiari sulla documentazione: quando consegnare la
documentazione, come valutare la qualità della documentazione, come garantire
l'aggiornamento della documentazione? In larga misura, questi standard e procedure
saranno descritti in documenti separati, come il Piano di controllo della configurazione o
il Piano di garanzia della qualità.
Per esempio un progetto potrebbe dichiarare di seguire le convenzioni per la
programmazione Java come definite da Oracle1
.
###  5. Attività di gestione

[Indice ^](#indice)
> sono guidate da obiettivi e priorità fissati per il progetto. Ad esempio, la
direzione dovrà presentare relazioni periodiche sullo stato e sullo stato di avanzamento del
progetto. Dovrà anche seguire alcune priorità nel bilanciamento di requisiti, tempi e costi.

### 6. Rischi

[Indice ^](#indice)
> I potenziali rischi devono essere identificati il prima possibile. Ci saranno sempre dei
rischi: l'hardware potrebbe non essere consegnato in tempo, il personale qualificato
potrebbe non essere disponibile quando necessario, le informazioni critiche potrebbero
mancare quando è necessario e così via. È piuttosto ingenuo supporre che un progetto di
sviluppo software funzioni sempre senza intoppi. Anche in campi consolidati, come quello
delle costruzioni, c'è sempre qualcosa che non va. Si dovrebbe diagnosticare precocemente
i rischi di un progetto software e fornire misure per affrontarli; vedere anche il capitolo 8 .
Più incerti sono i vari aspetti del progetto, maggiori sono i rischi.

### 7. Personale 

[Indice ^](#indice)
> In momenti diversi il progetto richiederà diverse quantità di personale, con
competenze diverse. L'inizio, la durata, l'importo e la competenza delle categorie di
personale sono elencati in questa voce.

### 8. Metodi e tecniche

[Indice ^](#indice)
> In questa sezione vengono forniti i metodi e le tecniche da utilizzare
durante l'ingegneria dei requisiti, la progettazione, l'implementazione e le prove. In genere,
qui viene descritto anche il modo in cui viene gestito il controllo della versione e della
configurazione per i componenti software. Gran parte della documentazione tecnica sarà
prodotta durante queste fasi. Si deve quindi precisare come sarà curata questa
documentazione.
Viene descritto l'ambiente di prova e le apparecchiature di prova necessari. Durante il
test, viene normalmente esercitata una pressione considerevole sull'apparecchiatura di
test. Pertanto, questa attività deve essere pianificata con attenzione. L'ordine in cui i
componenti vengono integrati e testati deve essere indicato esplicitamente. Inoltre,
devono essere indicate le procedure da seguire durante i test di accettazione, ovvero i
test sotto la supervisione dell'utente. I test sono discussi nel Capitolo 13.

### 9. Garanzia di qualità

[Indice ^](#indice)
> Quale organizzazione e procedure verranno utilizzate per garantire che il
software in fase di sviluppo soddisfi i requisiti di qualità dichiarati? I molti aspetti di un Piano
di Assicurazione della Qualità possono anche essere trattati in un documento separato. Il 
documentazione, come valutare la qualità della documentazione, come garantire
l'aggiornamento della documentazione? In larga misura, questi standard e procedure
saranno descritti in documenti separati, come il Piano di controllo della configurazione o
il Piano di garanzia della qualità.
Per esempio un progetto potrebbe dichiarare di seguire le convenzioni per la
programmazione Java come definite da Oracle1
.
### 10. Pacchetti di lavoro (workpackages) 

[Indice ^](#indice)
> I progetti più grandi devono essere suddivisi in attività,
parti gestibili che possono essere allocate ai singoli membri del team. Ciascuna di queste
attività deve essere identificata nel piano di progetto. La scomposizione gerarchica del
progetto è rappresentata in una struttura di scomposizione del lavoro (vedi anche Sezione
8.4 ).

### 11. Risorse 

[Indice ^](#indice)
> Durante il progetto sono necessarie molte risorse. L'hardware, i cicli della CPU e gli
strumenti necessari per supportare il progetto sono elencati in questa voce. Occorre inoltre
indicare il personale necessario per le varie fasi del processo.

### 12. Budget e programma 

[Indice ^](#indice)
> Il budget totale per il progetto deve essere assegnato alle varie attività
come indicato nella struttura di ripartizione del lavoro. Anche le attività devono essere
programmate in tempo, ad esempio utilizzando un grafico PERT (vedi Sezione 8.4 ). In questa
rubrica è indicato anche il modo in cui vengono tracciate le risorse e le altre spese. Il tema
della stima dei costi e dei tempi è trattato ampiamente nel Capitolo 7 .

### 13. Cambiamenti

[Indice ^](#indice)
> È stato affermato in precedenza che i cambiamenti sono inevitabili. Bisogna
garantire che questi cambiamenti siano gestiti in modo ordinato. Sono quindi necessarie
procedure chiare su come verranno gestite le modifiche proposte. Se il processo è agile, ogni
iterazione comporta modifiche e queste vengono gestite in modo leggero. In realtà, non sono
visti come cambiamenti. Nei processi più pesanti, ogni modifica proposta deve essere
registrata e rivista. Quando una richiesta di modifica è stata approvata, è necessario stimarne
l'impatto (costo). Infine, la modifica deve essere incorporata nel progetto. Le modifiche che
vengono immesse tramite la porta sul retro portano a codice strutturato male,
documentazione inadeguata e superamento di costi e tempi. Poiché le modifiche portano a
versioni diverse sia della documentazione che del codice, le procedure da seguire per gestire
tali modifiche vengono spesso gestite nel contesto di un piano di controllo della
configurazione.
> 
### 14. Consegna

[Indice ^](#indice)
> Devono essere indicate le procedure da seguire per la consegna dell'impianto al
cliente.
