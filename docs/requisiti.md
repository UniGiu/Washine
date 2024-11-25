**GLOSSARIO**
\-	**lavaggio**: insieme di informazioni relative a un lavaggio programmato da un lavandaio con carichi degli utenti   
\-	**carico**: quantità di vestiti in kg portata da ogni utente in un lavaggio  
\-	**comunità**: insieme di utente accomunati da un lavandaio   
\-	**lavandaio**: utente che crea un lavaggio   
\-	**lavatore**: utente che inserisce un carico nel sistema  
\-	**adesione**: ingresso di un utente in una comunità  
\-	**disconnessione**: disconnessione da una comunità  
\-	**indirizzo**: luogo dove ci ritrova per la consegna o per il ritiri del proprio carico   
\-	**calcolatore di carico**: tabella interattiva per il calcolo del carico con valori medi per capo  
\-	**caratteristiche di lavaggio**: caratteristiche necessarie alla creazione di un lavaggio  
\-	**opzioni di lavaggio**: caratteristiche opzionali di un lavaggio


**Elicitazione**

L’elicitazione dei requisiti è avvenuta attraverso un’analisi dello scenario. Durante una sessione di brainstorming ci siamo concentrati sull’identificazione dei potenziali profili di utenti tipo della nostra applicazione. Il nostro obiettivo era di individuare i bisogni principali per ciascuno, comprendendo le loro necessità e traducendole in requisiti.  
   
Abbiamo definito 6 potenziali profili utente:  
 

| Utente | Profilo/contesto | Bisogni/requisiti degli attori |
| :---- | :---- | :---- |
| Nonna Pina | Si ritrova con le amiche e passano del tempo insieme durante il lavaggio che offrono a turno amichevolmente senza dividere ogni volta le spese. Spesso, se non ha impegni, il ritrovo conviviale continua il giorno successivo perché anche per non portare pesi eccessivi stende tutti i panni a casa sua. Al figlio e ai nipoti lava e stira anche le camicie. Esperta del dominio dell’applicazione. | Granularità alta delle informazioni sul lavaggio Interfaccia utente semplice Segnalare disponibilità asciugatura Differenziare i lavatori per lavaggio Pesare i vestiti e specificare il peso massimo  |
| I Colleghi | Il loro team fa spesso lavori lontano da casa o affittano una stanza o semplicemente non hanno una lavatrice e ne sanno il minimo indispensabile. Hanno un guardaroba ridotto che va lavato frequentemente o dei vestiti che vanno lavati in maniera specifica. I lavaggi vengono organizzati da uno di loro, o a turno, in lavanderie a gettone dove si ritrovano | Dividere il carico in parti uguali Bassa granularità delle informazioni sul lavaggio (ma appropriata) Impostazione entità massima dei carichi  Esplicitare indirizzo, e ora di ritrovo Esplicitare i costi e sistema di ripartizione Asciugatrice Interpretare correttamente le indicazioni di lavaggio sulle etichette vestiario Pesare i vestiti e specificare il peso massimo |
| I Condomini | Si conoscono da tempo e spesso si scambiano favori. Alcune famiglie sono numerose e capitano delle eccedenze di vestiti da lavare, soprattutto con figli piccoli. Solitamente i lavatori ritirano a orari diversi i capi dal lavandaio e li fanno asciugare a casa qualcuno si trattiene durante il lavaggio. In qualche condominio c’è una lavanderia comune. | Nessun indirizzo Intervalli orari di disponibilità per il ritiro Durata del lavaggio Pesare i vestiti e specificare il peso massimo  |
| L’Utente Base | Non ha/vuole una lavatrice e non vuole usare una lavatrice a gettoni, forse perché non sa usarla. Si appoggia a uno o più conoscenti lavandai ai quali su appuntamento porta i vestiti a casa o a un punto di incontro . | Intervalli orari di disponibilità per il ritiro Indirizzo di raccolta o consegna Esplicitare i costi e sistema di ripartizione |
| Il Perditempo | Spesso in pensione, molto attivo, offre il servizio ad amici, parenti e conoscenti spesso non così autosufficienti raccogliendo i panni da ciascuno al domicilio (o al bar) e lavandoli a casa propria o in lavanderia a gettoni. Si occupa anche della riconsegna. Dai lavatori riceve riconoscenza e generalmente un rimborso delle spese. | Durata del lavaggio Pesare i vestiti e specificare il peso massimo Interpretare correttamente le indicazioni di lavaggio sulle etichette vestiario Esplicitare i costi e sistema di ripartizione Indicare indirizzi di ritiro e consegna |
| I Parrocchiani | I parrocchiani hanno raccolto fondi per una lavatrice di comunità per i bisognosi che in giorni e orari prefissati si possono presentare con i loro indumenti che verranno accettati e lavati fino a esaurimento disponibilità. Parte del carico può essere riservata per chi non ha accesso a Washine e si presenta direttamente in parrocchia.  | Programmare un calendario lavaggi Gestire l’accessibilità ai lavaggi e ai carichi Inserimento carichi di utenti non registrati Risalire ai proprietari degli indumenti Granularità minima delle informazioni di lavaggio Impostazione entità massima dei carichi Pesare i vestiti e specificare il peso massimo  |

Abbiamo inoltre individuato 3 ruoli: 

| Ruolo | Profilo/contesto | Bisogni/requisiti |
| :---- | :---- | :---- |
| Lavandaio | Offre ai lavatori un servizio di lavaggio e fornisce le informazioni necessarie per decidere se aderire. Può annullare un lavaggio. Propone l’adesione degli utenti alla sua comunità e può disporne la sconnessione. Può essere a sua volta lavatore.  | Fornire un lavaggio e gestirne le informazioni e l’accesso  Gestire la composizione della comunità Coordinare il processo relativo al lavaggio |
| Lavatore |  Aderisce alla comunità di lavaggio di un lavandaio, partecipa ai lavaggi che gli vengono offerti indicando l’entità del suo carico. Può essere a sua volta lavandaio. | Fornire informazioni sul carico Ricevere informazioni sui lavaggi  |
| Admin | Per qualche motivo ha deciso di installare Washine su un server e offrire il servizio, ma non è necessariamente esperto di informatica e pur non essendo un richiestissimo ingegnere informatico non ha molto tempo da dedicare alla gestione dell’applicazione. La sua attività principalmente si limita  a saltuari aggiornamenti e a considerare eventuali problematiche tecniche segnalate dagli utenti. Quando strettamente necessario può intervenire sulle attività degli utenti rimuovendo lavaggi, eliminando utenti. Può limitare l’accesso a un insieme ristretto di utenti, o impedirlo ad altri. Può essere | Monitorare e tracciare l’utilizzo dell’applicazione Installare e configurare l’applicazione con competenze basse Accedere al codice dell’applicazione Eliminare lavaggi Eliminare o resettare utenti Regolare l’accesso tramite una lista nera/bianca di utenti |

Una volta terminato questo passaggio, abbiamo identificato i requisiti comuni tra tutti e quelli tra loro contrastanti in modo da considerare quelli più rilevanti (e se necessario trovare dei compromessi) per soddisfare le necessità principali di ogni utente.  
   
Abbiamo poi adottato il modello MoSCow per classificare i requisiti, garantendo una corretta implementazione e gestione delle priorità.  
   
 

| Must Have | Should Have | Could Have | Won’t Have |
| :---- | :---- | :---- | :---- |
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |

   

