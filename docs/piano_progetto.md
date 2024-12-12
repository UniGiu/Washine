
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
<!-- > Nell'introduzione al piano di progetto, vengono forniti il background e la storia del progetto, insieme ai suoi obiettivi, i risultati del progetto, i nomi delle persone responsabili e una sintesi del progetto.-->

#### Background, storia, e obiettivi

Questo progetto è stato concepito come parte dell'esame universitario di Ingegneria del Software.  
Rispetto ad un progetto tipico presenta quindi caratteristiche diverse per contesto e durata: si tratta di 3 studenti che in un paio di mesi devono documentare e sviluppare un software con strumenti dati a priori seguendo metodologie e pratiche per loro nuove.  
L'obiettivo è sperimentare insieme quanto visto e studiato durante il corso e dimostrare aver ben compreso e messo in pratica con efficacia le competenze necessarie per lo sviluppo di un progetto software.  
Questo consisterà nel provare a fare tutto nel tempo e con il numero di ore previsti e poi rivedere se e come lo abbiamo fatto.

#### Risultati del progetto

Ci siamo riusciti? Questa parte è ancora da scrivere, speriamo di sì!

#### Persone coinvolte

-   Suardi Paolo, mat 1087357
-   Tilli Giuseppe, mat 1086587
-   Toffalori Chiara, mat 1080710

#### Sintesi del progetto

Washine è un applicazione web free open source progettata per facilitare l'organizzazione e la condivisione del lavaggio di indumenti tramite lavatrici domestiche o a gettoni. 
Il software viene fornito agli utenti come applicazione web in modo tale che questi possano pianificare un lavaggio condiviso di indumenti specificandone le caratteristiche oppure partecipare a un lavaggio già pianificato tra quelli disponibili specificando la quota di indumenti (peso).  
Inoltre è previsto un utente Amministratore che può monitorare l'attività degli utenti e intervenire in casi particolari. L'applicazione sarà progettata in modo tale che l'intervento dell'amministratore sia minimo e con ruolo prevalente di facilitatore e supporto tecnico, con ridotta attività di sorveglianza. Le responsabilità di amministrazione degli utenti saranno attribuite principalmente all'organizzatore del lavaggio.

L'applicazione ha come scopo quello di ridurre i consumi energetici relativi ai lavaggi e alla produzione di macchine lavatrici e di rafforzare i legami sociali all'interno di piccole comunità. Non ci aspettiamo che Washine sia la soluzione definitiva ai gravi problemi dell'isolamento sociale, della povertà, della disponibilità di energia e del ruolo del suo utilizzo nel deterioramento dell'habitat umano.  
Il progetto infatti si propone solo di mettere a disposizione di chiunque sia interressato uno strumento per facilitare una pratica di utilità sociale e di poter essere fonte di ispirazione e di incoraggiamento per iniziative simili.

L'applicazione si rivolge a piccole comunità di utenti finali accumunati dalla prossimità geografica e da un legame di reciproca conoscenza e fiducia (amici, condomini, colleghi di lavoro, parrocchiani, frequentatori di un bar, studenti fuori sede, abitanti di un quartiere...). Successivamente, anche in virtù della natura free open source del progetto, potrebbe essere prevista un'estensione della base di utenza e un'aumento di configurazioni del prodotto anche per finalità commerciali e di micro imprenditorialità (ad esempio supportare l'attività di lavandaio con una funzionalità di ritiro e consegna a domicilio degli indumenti ecc).

L'applicazione non è pensata per la scalabilità verticale, ma per quella orizzontale: le scelte della licenza free open source e di ridurre le attività di amministrazione al minimo indispensabile sono state prese per favorire più installazioni il più possibile sostenibili in termini economici e di impegno richiesto. Ciascuna installazione avrebbe un numero di utenti limitato (utenti non necessariamente in relazione diretta con chi fornisce l'applicazione) e potrebbe essere riconfigurata e riadattata a servire esigenze e bisogni specifici relativi al contesto culturale e sociale di utilizzo. La sostenibilità dovrebbe essere accompagnata da altrettanta accessibilità in termini di infrastruttura e competenze necessarie per la manutenzione.

L'applicazione è ipotizzata come integrata in un ecosistema tecnologico più vasto e non persegue la user retention, perciò eviteremo la replicazione di elementi funzionali già presenti nel contesto, rendendo l'applicazione più leggera e usabile. Ad esempio il flusso comunicativo tra gli utenti non avverrà tramite un sistema dedicato: gli utenti si conoscono tra di loro e hanno già un modo preferito di interagire quando si frequentano o tramite applicazioni di messaggistica istantanea o con telefonate o sms. Non c'è bisogno di obbligarli ad apprendere e utilizzare un sistema in più, l'applicazione si limiterà a inviare email per il recupero delle password o altre comunicazioni di servizio strettamente necesssarie.  

###  2. Modello di processo

[Indice ^](#indice)
<!-- > Nel Capitolo 1 è stato introdotto un semplice modello del ciclo di vita
per discutere le varie attività da affrontare in un progetto di sviluppo software. Esistono
molte varianti di questo modello di processo, alcune delle quali sono discusse nel Capitolo 3.
Per ogni progetto si deve decidere l'esatto modello di processo da seguire: quali attività
intraprendere, quali pietre miliari possono essere identificate, come si accerta se tali pietre
miliari vengono raggiunte e quali sono i percorsi critici. Diversi tipi di progetti hanno
caratteristiche diverse e quindi richiedono modelli di processo diversi.
Ad esempio, il project plan potrebbe stabilire che si utilizzerà un processo agile SCRUM.
>-->
 Per lo sviluppo di Washine abbiamo deciso di adottare il modello RAD (Rapid Application Development). Questo modello fa uso di un approccio incrementale e iterativo, 
 promuove il riutilizzo e l'uso di strumenti automatizzati e sottolinea l'importanza del coinvolgimento del cliente. RAD si concentra su cicli di sviluppo brevi e, al 
 contrario della maggior parte degli altri metodi di sviluppo, prevede che vengano fissati dei periodi di tempo (time box) all'interno dei quali cercheremo di realizzare funzionalità in base a priorità assegnate.

 #### Fasi principali
 Le attività previste dal modello RAD per il progetto sono:
 1.  **Pianificazione dei requisiti**
 2.  **Progettazione dell'applicazione**
 3.  **Costruzione**
 4.  **Taglio**

   
 #### Vantaggi del modello RAD per il progetto 
 La scelta del modello RAD è particolarmente adatta per il nostro progetto per i sequenti motivi:
 -  Il numero relativamente ridotto dei membri del nostro team risulta adatto all'approccio usato in RAD
 -  La riduzione dei tempi di sviluppo appare particolarmente vantaggiosa per la rapidità richiesta dal progetto che ha una scadenza assoluta definita a priori
 -  La struttura iterativa permette di adattare i requisiti e le funzionalità al cambiamento garantendo una maggiore flessibilità e di poter rivedere le nostre scelte tempestivamente
 -  La possibilità di realizzare più versioni incrementali del sistema riduce il rischio di fraintendimenti sui requisiti e permette di avere un prodotto sempre funzionante

###  3. Organizzazione del progetto 

[Indice ^](#indice)
<!-- > Il rapporto del progetto con altri enti e l'organizzazione del
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
team sono discusse nel Capitolo 5 .-->

#### Entità coinvolte

Il progetto coinvolge i docenti per la valutazione finale e nel fornire un riscontro e supporto nelle fasi intermedie.
Il progetto che definiamo open source non ha ancora una base di utenti reali a cui fare riferimento. Ci troveremo noi stessi a interpretare il ruolo di un set utenti tipo ogni qual volta ce ne sarà bisogno. In questa situazione c'è il rischio che gli utenti immaginari non siano molto rappresentativi di quelli realmente interessati all'applicazione, ma anche il vantaggio di averli sempre con noi per il JRP e i JAD previsti dal RAD.

#### Team

La scelta del modello di processo RAD prevede un team SWAT. Questo nel nostro caso non è di fatto completamente praticabile poiché i membri del team non sono particolarmente skilled con gli advanced tools e sia le persone che i tools sono dati a priori e non sostituibili con altri se ce ne fosse bisogno. Partendo da questa assunzione proveremo comunque ad agire da team SWAT, faremo brevi e frequenti riunioni quando saremo nella stessa aula tra una lezione e l'altra. La messaggistica online si presta, tra le altre cose, a una continua sessione di brainstorming.

Come tutti i principianti non ci verrà difficile sentirci orgogliosi del nostro lavoro come i veri team SWAT, ma poiché lo sviluppo di Washine non è la nostra unica occupazione e non abbiamo tutti gli stessi orari ci troveremo a lavorare in part-time. Tra i modelli organizzativi presi in considerazione quello a matrice sembra il più adatto ad integrare lo SWAT per gestire questa situazione. 
Ruoli e responsabilità saranno quindi attribuiti con l'assegnazione di una o più persone alle diverse specializzazioni in base alle necessità. Vale anche qui la considerazione che la specializzazione delle unità base in questo caso è in divenire, definita a partire delle responsabilità assegnate.

Probabilmente in certi casi ci riconosceremo quindi anche in una forma molto blanda del modello programmatore capo: alcune unità base specializzate saranno formate da più persone (tra le poche che compongono il team) tra le quali una responsabile di una parte di codice in accoppiata o assistita da un'altra, e una terza che farà da revisore e si prenderà carico di svolgere anche il ruolo del bibliotecario.


###  4. Standard, linee guida, procedure 

[Indice ^](#indice)
<!-- > I progetti software sono grandi progetti. Di solito, molte
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
>-->
Allo scopo di mantenere uno stile di codifica unificato, facilitare la manutenzione, migliorare la leggibilità del software e quindi velocizzare il lavoro nel suo complesso abbiamo deciso di uniformarci alle 
regole previste dalla [Google Java Style Guide][], uno standard ad ampio spettro, che va dalla prescrizione relativa alla formattazione del codice fino alla nomenclatura di classi, package e metodi...

In relazione a tale scelta si è deciso di utilizzare [google-java-format][], un programma che, sotto forma di plug-in, riformatta il codice rendendolo compatibile con lo standard.

Come linea guida generale faremo largo uso delle branches, tramite Github, per mantenere il branch principale privo di errori e per evitare di aggiungere modifiche indesiderate o non testate nel codice 
principale; per cambiamenti ai documenti o al codice lavoreremo principalmente su branch secondari dedicati a quella specifica modifica o funzionalità per poi fare il merge con il branch main a 
lavoro finito, una volta verificato che non ci sono conflitti e che la modifica non introduce problemi prima inesistenti.

Per l'aggiornamento della documentazione e attività di approvazione di proposte utilizzeremo un approccio che segue la maggioranza: essendo in tre la proposta deve essere approvata almento da due membri.

[Google Java Style Guide]: https://google.github.io/styleguide/javaguide.html
[google-java-format]:https://github.com/google/google-java-format/tree/master

###  5. Attività di gestione

[Indice ^](#indice)
<!-- > sono guidate da obiettivi e priorità fissati per il progetto. Ad esempio, la
direzione dovrà presentare relazioni periodiche sullo stato e sullo stato di avanzamento del
progetto. Dovrà anche seguire alcune priorità nel bilanciamento di requisiti, tempi e costi.
>-->
Il lavoro è organizzato  in timebox, ciascuno dei quali sarà allineato con una delle fasi specifiche del progetto. Durante ciascun timebox, rimarremo in contatto tramite messaggi online e brevi discussioni organizzate tra una lezione e l’altra, per confrontarci rapidamente sulle attività in corso e risolvere eventuali dubbi. Alla scadenza invece ci riuniremo per valutare i progressi compiuti rispetto agli obiettivi, identificare eventuali problemi emersi e ridefinire i passi successivi per l’avanzamento.

Per assicurare il rispetto delle scadenze utilizzeremo un foglio condiviso contentente un calendario suddiviso in settimane, con l'indicazione degli obiettivi specifici da raggiungere entro la fine di ciascun periodo. 

Utilizzeremo inoltre la Kanban board per gestire il flusso di lavoro durante lo sviluppo del software che permette di fornire una panoramica di tutte le attività e il loro stato attuale. L'andamento del progetto risulterà quindi sempre monitorato, i membri del team potranno vedere chi è responsabile di ciascuna attività e comunicare tramite issue.

Questo ci aiuterò a mantenere il lavoro allineato con le priorità del progetto, garantendo che tutti rimangano aggiornati e che il lavoro si integri correttamente.

Non verranno verbalizzate le riunioni e la gestione sarà piuttosto informale e collettiva.

### 6. Rischi

[Indice ^](#indice)
<!-- > I potenziali rischi devono essere identificati il prima possibile. Ci saranno sempre dei
rischi: l'hardware potrebbe non essere consegnato in tempo, il personale qualificato
potrebbe non essere disponibile quando necessario, le informazioni critiche potrebbero
mancare quando è necessario e così via. È piuttosto ingenuo supporre che un progetto di
sviluppo software funzioni sempre senza intoppi. Anche in campi consolidati, come quello
delle costruzioni, c'è sempre qualcosa che non va. Si dovrebbe diagnosticare precocemente
i rischi di un progetto software e fornire misure per affrontarli; vedere anche il capitolo 8 .
Più incerti sono i vari aspetti del progetto, maggiori sono i rischi. -->

Trattandosi di un progetto da sviluppare in ambito universitario i potenziali rischi sono per lo più di natura organizzativa. Uno dei 
principali ostacoli alla buona riuscita del progetto è la necessità di conciliare il lavoro sul progetto con altri impegni, compresi 
quelli accademici per altri corsi: questo può tradursi nella difficoltà di rispettare i tempi e i requisiti richiesti e prescelti per 
la consegna, o di garantire un'adeguata partecipazione di tutti i membri in modo continuo.

Un altro possibile problema è l'eventualità che un membro del team abbandoni il progetto senza o con insufficente preavviso 
sovraccaricando gli altri membri e compromettendo la scadenza. Anche la comunicazione e il coordinamento sono elementi fondamentali 
in un progetto di questo tipo e la loro mancanza può rallentare tutto il processo e l'avanzamento del lavoro. 

Ci sono poi le eventuali difficoltà legate all'uso di strumenti e metodi nuovi la cui padronanza potrebbe richiedere tempi più lunghi 
di quelli preventivati.

### 7. Personale 

[Indice ^](#indice)
<!-- > In momenti diversi il progetto richiederà diverse quantità di personale, con
competenze diverse. L'inizio, la durata, l'importo e la competenza delle categorie di
personale sono elencati in questa voce.-->

Siamo in 3 persone e a meno che qualcuno abbandoni il progetto saremo in 3, sempre gli stessi, dall'inizio alla fine.
Una stima sommaria della ripartizione ed estensione delle necessità di personale potrebbe essere questa:

| Competenza                         | durata      | ore tot | importo |
| :--------------------------------- | :---------: | ------: | :------ | 
| Teoria ingegneria del software (Documentazione, requisiti e design) | 6 settimane | 60      | 3       |
| Sviluppo e test Java                      | 3 settimane | 16      | 2       |
| Sviluppo e test Vaadin + Java             | 4 settimane | 32      | 3       |
| COTS Vaadin                        | 1 settimane | 4       | 1       |
| Sviluppo e test jOOQ  + Java              | 1 settimana | 8       | 1       |


### 8. Metodi e tecniche

[Indice ^](#indice)
<!-- > In questa sezione vengono forniti i metodi e le tecniche da utilizzare
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
>-->

- Ingegneria requisiti: brainstorming e casi d'uso
- Progettazione: UML, metriche, design pattern
- Implementazione: Github, prototipazione incrementale
- Test: unitari, di integrazione

### 9. Garanzia di qualità

[Indice ^](#indice)
<!-- > Quale organizzazione e procedure verranno utilizzate per garantire che il
software in fase di sviluppo soddisfi i requisiti di qualità dichiarati? I molti aspetti di un Piano
di Assicurazione della Qualità possono anche essere trattati in un documento separato. Il 
documentazione, come valutare la qualità della documentazione, come garantire
l'aggiornamento della documentazione? In larga misura, questi standard e procedure
saranno descritti in documenti separati, come il Piano di controllo della configurazione o
il Piano di garanzia della qualità.
Per esempio un progetto potrebbe dichiarare di seguire le convenzioni per la
programmazione Java come definite da Oracle1-->

Documenteremo quali criteri di qualità del software e dei processi abbiamo preso come riferimento e quali compromessi abbiamo trovato per soddisfarli al meglio nel contesto.


### 10. Pacchetti di lavoro (workpackages) 

[Indice ^](#indice)
<!-- > I progetti più grandi devono essere suddivisi in attività,
parti gestibili che possono essere allocate ai singoli membri del team. Ciascuna di queste
attività deve essere identificata nel piano di progetto. La scomposizione gerarchica del
progetto è rappresentata in una struttura di scomposizione del lavoro (vedi anche Sezione
8.4 ).-->

 Il nostro progetto non è particolarmente grande, ma per la parte di produzione di codice è sicuramente utile una divisione esplicita delle parti che possono essere
 allocate ai membri del team, anche in considerazione delle caratteristiche organizzative del progetto.
 La gerarchia è individuata in base alle specialità delle nostre unità base (la cui consistenza verrà poi definita da una stima del carico di lavoro richiesto).
- Interfaccia web domanda
- Interfaccia web offerta
- Interfaccia web componenti comuni
- Database
- Core
- Istruzioni e informazioni per gli utenti

Per la parte di produzione della documentazione piuttosto che suddividere il lavoro per documento-argomento (piano di progetto, specifica dei requisiti...) preferiamo la condivisione orizzontale delle responsabilità assegnandoci parti di documento in base alle preferenze, al carico di lavoro e alla necessità del momento. Una prima versione dei documenti verrà stesa con scadenza decisa in previsione del loro utilizzo nella produzione o in concomitanza con le lavorazioni attinenti. Rimarrà la possibilità di iterazioni che permettano correzioni, modifiche e integrazioni. 

### 11. Risorse 

[Indice ^](#indice)
<!-- > Durante il progetto sono necessarie molte risorse. L'hardware, i cicli della CPU e gli
strumenti necessari per supportare il progetto sono elencati in questa voce. Occorre inoltre
indicare il personale necessario per le varie fasi del processo.
>-->
Ognuno utilizzerà il proprio hardware e porterà avanti lo sviluppo del software utilizzando i seguenti strumenti:

- Eclipse: utilizzato come IDE principale per la programmazione e lo sviluppo del codice.
- Papyrus: impiegato per la modellazione e la creazione dei diagrammi UML necessari alla progettazione.
- GitHub: utilizzato per il Configuration Management e la gestione collaborativa del codice sorgente.
- SQLite: scelto per la creazione e gestione del database embedded.
- Vaadin: framework open source adottato per lo sviluppo di interfacce utente (UI) scalabili e intuitive.
  
Per quanto riguarda il personale il team non verrà suddiviso in maniera gerarchica, le responsabilità verranno assegnate e riassegnate in base alle necessità.

### 12. Budget e programma 

[Indice ^](#indice)
<!-- > Il budget totale per il progetto deve essere assegnato alle varie attività
come indicato nella struttura di ripartizione del lavoro. Anche le attività devono essere
programmate in tempo, ad esempio utilizzando un grafico PERT (vedi Sezione 8.4 ). In questa
rubrica è indicato anche il modo in cui vengono tracciate le risorse e le altre spese. Il tema
della stima dei costi e dei tempi è trattato ampiamente nel Capitolo 7 .-->

Non essendoci risorse finanziarie a disposizione l'unica risorsa che possiamo allocare è il nostro tempo. Ci siamo dati come budget
40 ore a persona ripartite come nello schema del punto [Personale](#7-personale) anche se vista la nostra mancanza di esperienza nei metodi e negli strumenti
la nostra stima è molto approssimativa e probabilmente le ore dedicate alle diverse attività saranno di più e diversamente distribuite. 
Abbiamo preparato un foglio di calcolo dove tracciamo e monitoriamo le ore spese e dove possiamo vedere se siamo in ritardo o anticipo rispetto alla programmazione per settimane. L'unità di tempo di riferimento per la programmazione è la settimana soprattutto per aderire alla scansione del tempo del resto delle nostre attività universitarie.

### 13. Cambiamenti

[Indice ^](#indice)
<!-- > È stato affermato in precedenza che i cambiamenti sono inevitabili. Bisogna
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
>-->
Essendo un gruppo ristretto non è presente un comitato per l'accettazione delle modifiche, i cambiamenti vengono approvati in modo rapido in base alla maggioranza. Sarà quindi un processo informale a cui seguirà documentazione.

### 14. Consegna

[Indice ^](#indice)
<!-- > Devono essere indicate le procedure da seguire per la consegna dell'impianto al cliente.--> 

Una pre-release del progetto consisterà nella condivisione del codice sorgente e della documentazione con i docenti sulla piattaforma Github a partite da un mese dal primo appello d'esame. La versione finale e la sua presentazione verranno consegnate in prossimità dell'esame orale (24/12/2024).
