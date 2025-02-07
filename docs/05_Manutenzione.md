# Manutenzione del software
Durante tutto il processo di sviluppo del software abbiamo effettuato diverse attività di manutenzione allo scopo di migliorare la qualità, la leggibilità e la stabilità del codice. Tali attività rientrano nelle seguenti categorie:
## Attività di manutenzione preventiva
Per aumentare la manutenibilità del sistema e migliorare la comprensione del codice abbiamo adottato diverse pratiche, tra cui l'aggiunta di commenti e della Javadoc per la maggior parte del codice, che ne hanno di sicuro velocizzato lo sviluppo e reso più semplice la risoluzione di problemi, così come potranno agevolare la futura manutenzione. Ad esempio, nel tentativo di individuare un bug in un metodo, il fatto che fosse presente la Javadoc, che descriveva ciò che la funzione avrebbe dovuto fare, ha reso il lavoro più semplice oltre ad aver favorito l'individuazione e la scelta del giusto metodo già implementato nel caso in cui un membro del gruppo aveva bisogno di una funzionalità prodotta da un altro membro, dato che molto spesso ci siamo trovati a lavorare in contemporanea su componenti di sistema diverse.

Un'ulteriore pratica volta al miglioramento della manutenibilità è stata quella di mantenere uno stile di codifica unificato, per facilitare la lettura del codice, cercando nei limiti del possibile di aderire agli standard di codifica e di usare il formatter tramite eclipse e provando a mantenere nomi di attributi, classi e metodi il più intuitivi e chiari possibili.

Abbiamo inoltre mantenuto man mano aggiornata la documentazione relativa alle modifiche apportate al sistema in modo che queste non rimanessero implicite, ma fossero in qualche modo attestate. Per esempio è stato sostituito di volta in volta il codice Sql per la creazione delle tabelle del database in tutti i casi in cui questo veniva cambiato oppure abbiamo modificato il diagramma delle classi quando veniva cambiato il design del sistema.
## Manutenzione correttiva
Tra le varie attività di manutenzione è stata la più dispendiosa in termini di tempo, ed è consistita principalmente nella risoluzione di malfunzionamenti, quando riscontrati, tramite l'utilizzo dell'applicazione e testing. 

Parte di questa manutenzione è ancora da svolgere.
## Manutenzione perfettiva
Anche se questo tipo di attività deriverà dal futuro ed effettivo utilizzo dell'applicazione, immedesimandosi nell'utente si è sempre cercato di migliorare l'interfaccia rendendola intuitiva e gradevole, ad esempio permettendo all'utente di vedere le caratteristiche dei lavaggi disponibili per le sue comunità ancor prima della sua partecipazione.

E' sicuramente da prevedere che in futuro si possa applicare il Refactoring per migliorare le prestazioni, ampliare il numero di funzionalità offerte o ridurre la complessità del sistema. Per esempio si potrebbe in qualche modo sostituire vari metodi caratterizzati da un numero di parametri troppo grande con dei metodi più snelli, o ancora, aggiungere funzionalità e migliorare l'interfaccia per l'utente amministratore.
## Refactoring
Abbiamo effettuato diverse operazioni di refactoring per migliorare il funzionamento del sistema nel suo complesso.
A tale scopo diversi strumenti (sotto forma di plugin di eclipse) ci hanno aiutato in queste attività. Grazie ai suggerimenti del tool SonarLint, che individua i "bad-smells", abbiamo rimosso delle variabili che potevano essere omesse, semplificato delle condizioni da valutare o eliminato codice duplicato. Tramite l'analisi statica proposta da StanIDE abbiamo cercato anche di aumentare l'astrazione, diminuire la complessità dei package e ridurne l'accoppiamento.
### Esempi di attività di refactoring:
#### Pattern Factory
Abbiamo cambiato il metodo con cui vengono istanziati gli oggetti del core dell'applicazione nella parte di codice relativa all'interfaccia grafica: se prima gli oggetti venivano istanziati direttamente dalla "GUI" tramite costruttore, il compito della creazione degli oggetti è stato trasferito, grazie all'implementazione del pattern Factory, a una classe apposita AbstractCoreFactory presente lato core:

##### Classe AbstractCoreFactory

![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/AbstractFatory.png)

#### Gestione delle eccezioni
Abbiamo cambiato anche il sistema di gestione delle eccezioni: inizialmente esisteva un unico tipo di eccezione, che veniva sollevata a livello del database e propagata per tutto il sistema, dal database all'interfaccia grafica, su tutti e tre i livelli. Il sistema è stato modificato introducendo un metodo di gestione disaccoppiato tra i vari componenti: qualora venga rilevato un problema in una query a livello database questo lancia una tipologia di eccezione che si cerca di catturare a livello del core; una volta che questa è stata catturata a sua volta il core lancia un'altra tipologia di eccezione che viene catturata poi dal componente che gestisce la GUI(il quale di seguito notifica l'errore all'utente). Ciò favorisce la compartimentazione e l'indipendenza tra i componenti:

##### Classe del database che cattura una SQLException e lancia una WashineDataException

![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/Exception1.png)

##### Classe del core che cattura la WashineDataException e lancia una WashineCoreException

![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/Exception2.png)

##### Classe della GUI che cattura la WashineCoreException e notifica l'utente dell'errore

![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/Exception3.png)
#### Conversione delle date
Un altro possibile esempio del Refactoring da noi effettuato può essere quello della creazione di una classe apposita per la conversione delle date piuttosto che la ripetizione costante del codice:
##### Classe WashineTimeUtils
![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/TimeConverter.png)
