# Manutenzione del software
Durante tutto il processo di sviluppo del software abbiamo effettuato diverse attività di manutenzione allo scopo di migliorare la qualità, la leggibilità e la stabilità del codice. Queste attività rientrano nelle seguenti categorie.
## Attività di manutenzione preventiva
Per aumentare la manutenibilità del sistema e migliorare la comprensione del codice abbiamo adottato diverse pratiche. L'aggiunta di commenti e della Javadoc per la maggior parte del codice ha di sicuro velocizzato lo sviluppo e reso più semplice la risoluzione di problemi così come agevolerà la futura manutenzione. Per esempio, nel tentativo di individuare un bug in un metodo, il fatto che fosse presente la Javadoc che descriveva quello che la funzione avrebbe dovuto fare ha reso il lavoro più semplice nonchè ha aiutato nell'individuare e scegliere il giusto metodo già implementato nel caso in cui un membro del gruppo aveva bisogno di una funzionalità prodotta da un altro, dato che molto spesso abbiamo lavorato in contemporanea su componenti di sistema diverse.
Un'ulteriore pratica volta a migliorare la manutenibilità è stata quella di mantenere uno stile di codifica unificato, per migliorare la leggibilità del codice, cercando nei limiti del possibile di aderire agli standard di codifica e di usare il formatter tramite eclipse così come provando a mantenere nomi di attributi, classi e metodi il più intuitivi e chiari possibili.
Abbiamo poi mantenuta aggiornata la documentazione relativa alle modifiche apportate di modo che queste non rimanessero implicite nel codice ma fossero in qualche modo attestate. Per esempio è stato aggiornato di volta in volta il codice Sql per la creazione delle tabelle del database ogni volta questo veniva cambiato oppure abbiamo modificato passo passo il diagramma delle classi quando veniva cambiato il design del sistema.
## Manutenzione correttiva
Tra le varie attività di manutenzione è stata la più dispendiosa in termini di tempo ed è consistita più che altro nella risoluzione di malfunzionamenti quando riscontrati tramite l'utilizzo dell'applicazione e testing. Parte di questa manutenzione è ancora da svolgere.
## Manutenzione perfettiva
Anche se questo tipo di attività deriverà dal futuro e reale utilizzo dell'applicazione, immedesimandoci nell'utente abbiamo sempre di cercato di migliorare l'interfaccia rendendola intuitiva e gradevole per esempio permettendo all'utente di vedere le caratteristiche dei lavaggi che sono disponibili per le sue comunità ancora prima della sua partecipazione.
Prevediamo sicuramente che in futuro si possa applicare il Refactoring per migliorare le prestazioni, ampliare il numero di funzionalità offerte o ridurre la complessità del sistema. Per esempio si potrebbe in qualche modo sostituire vari metodi con un numero di parametri troppo grande con dei metodi più snelli, o ancora aggiungere funzionalità e migliorare l'interfaccia per l'utente amministratore.
## Refactoring
Abbiamo effettuato diverse operazioni di refactoring per migliorare il funzionamento del sistema nel suo complesso.
A tale scopo diversi strumenti(sotto forma di plugin di eclipse) ci hanno aiutato in queste attività. Grazie ai suggerimenti del tool SonarLint che individua i "bad-smells" abbiamo rimosso delle variabili che potevano essere omesse, semplificato delle condizioni da valutare o eliminato codice duplicato. Tramite l'analisi statica proposto da StanIDE abbiamo cercato di aumentare l'astrazione, diminuire l'accoppiamento e la complessità dei package.
### Esempi di attività di refactoring
#### Pattern Factory
Abbiamo sostituito il metodo con cui vengono istanziati gli oggetti del core dell'applicazione nella parte di codice relativa all'interfaccia grafica: se prima gli oggetti venivano istanziati direttamente dalla "GUI" tramite costruttore, il compito della creazione degli oggetti è stato trasferito, grazie all'implementazione del pattern Factory, a una classe apposita AbstractCoreFactory presente lato core.

##### Classe AbstractCoreFactory

![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/AbstractFatory.png)

#### Gestione delle eccezioni
Oltre a ciò abbiamo cambiato il sistema di gestione delle eccezioni. Inizialmente esisteva un unico tipo di eccezione che veniva sollevata a livello del database e propagata per tutto il sistema, dal database all'interfaccia grafica su tutti e tre i livello. Questo è stato modificato introducendo un metodo di gestione disaccoppiato trai vari componenti: qualora viene rilevato un problema database questo lancia una tipologia di eccezione che si cerca di catturare a livello del core, una volta che questa è catturata a sua volta il core lancia un'altra tipologia di eccezione che viene catturata poi dal componente che gestisce la GUI(il quale poi notifica l'errore all'utente). Questo favorisce la compartimentazione l'indipendeza trai componenti.

##### Classe del database che cattura una SQLException e lancia una WashineDataException

![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/Exception1.png)

###### Classe del core che cattura la WashineDataException e lancia una WashineCoreException

![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/Exception2.png)

##### Classe della GUI che cattura la WashineCoreException e notifica l'utente dell'errore

![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/Exception3.png)
#### Conversione delle date
Un altro possibile esempio di Refactoring da noi fatto può essere la creazione di una classe apposita per la conversione delle date piuttosto che la ripetizione costante del codice.
##### Classe WashineTimeUtils
![alt text](https://github.com/UniGiu/Washine/blob/Manutenzione/docs/Immagini/TimeConverter.png)






















