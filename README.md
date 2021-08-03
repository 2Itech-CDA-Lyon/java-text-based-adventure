# Jeu d'aventure texte

L'objectif de ce projet est de créer un [jeu d'aventure texte](https://fr.wikipedia.org/wiki/Jeu_vid%C3%A9o_textuel) en ligne de commandes. Le jeu doit décrire au joueur ce que son personnage voit et ce qui se passe autour de lui; le joueur doit entrer des commandes afin de se déplacer et d'agir sur son environnement.

## Mission 1: lieux et navigation

Avant toute chose, il faut que le jeu décrive au joueur où il se trouve, et qu'il puisse se déplacer d'un endroit à l'autre. Le déroulement du jeu pourrait ressembler à l'exemple suivant:

<details>
<summary>Exemple</summary>

> You are in the bedroom. West is the bathroom, north is the corridor.

`west`

> You are in the bathroom. East is the bedroom.

`west`

> You cannot go into that direction!

> You are in the bathroom. East is the bedroom.

`east`

> You are in the bedroom. West is the bathroom, north is the corridor.

</details>

Afin d'obtenir ce résultat, implémenter les classes ci-après en suivant les spécifications fournies.

### `Room`

- Représente un lieu dans lequel le joueur peut se trouver.

| Méthode | Description |
|---|---|
| _**String** getName()_ | Renvoie le nom du lieu (exemple: `"bedroom"`) |
| _**Room** getRoomInDirection(**Direction** direction)_ | Renvoie le lieu où l'on arrive lorsque l'on part de ce lieu et qu'on emprunte la direction passée en paramètre (exemple: depuis la chambre à coucher, en passant la direction ouest, on devrait obtenir la salle de bain) |

### `Direction`

- Représente une direction que le joueur peut emprunter pour se déplacer d'un lieu à l'autre.

| Méthode | Description |
|---|---|
| _**String** getName()_ | Renvoie le nom de la direction (exemple: `"north"`) |

### `Game`

- Représente une partie jouée par le joueur.

| Méthode | Description |
|---|---|
| _**void** setup()_ | Initialise la partie en créant les objets de l'univers (les lieux et les directions) et en les associant les uns aux autres de la manière adéquate, et détermine le lieu de départ |
| _**void** update()_ | Décrit un cycle d'exécution de la partie: décrire le lieu courant, attendre une saisie de l'utilisateur, vérifier qu'elle correspond à une direction, changer de lieu si cette direction est empruntable depuis le lieu dans lequel on se trouve actuellement |
| _**boolean** isRunning()_ | Permet de savoir si la partie est en cours (`true`) ou si elle est terminée (`false`) |
| _**Room** getCurrentRoom()_ | Renvoie le lieu dans lequel le joueur se trouve actuellement |

### `App`

- Point d'entrée de l'application.

| Méthode | Description |
|---|---|
| _**static void** main(**String** args)_ | Processus principal. Crée une nouvelle partie et l'initialise, puis lui demande de réaliser un cycle d'exécution tant qu'elle est en cours. |

## Mission 2: objets et interactions

Maintenant que nos joueurs sont capables de se déplacer d'un lieu à une autre, il faudrait ajouter des éléments (objets, personnages, monstres…) avec lesquels ils pourront interagir.

<details>
<summary>Exemple</summary>

> You are in the bedroom. West is the bathroom, north is the corridor. There is a bed and a mirror.

`use bed`

> You take a quick nap. You feel refreshed!

`use mirror`

> You see your reflection. Looking good!

`open mirror`

> This does not open!

`talk to mirror`

> Silence...

</details>

### 1. Intégrer des objets à l'univers

- Écrire une classe `Item`, qui représente les éléments interactifs de l'univers.
- Chaque élément doit avoir un nom.
- Chaque pièce peut contenir une quantité indéterminée d'éléments. La liste des éléments visibles doit être affichée automatiquement dans chaque pièce.

### 2. Interagir avec des éléments

- Écrire une classe `Command` qui représente une commande que l'utilisateur peut entrer dans la console.
- Chaque commande doit avoir un texte par défaut qui s'affichera si jamais l'utilisateur tente de l'utiliser avec un élément qui n'a pas été prévu pour (exemple: `talk to mirror`).
- Chaque élément peut réagir à un nombre indéterminé de commandes. Dans un premier temps, utiliser une commande particulière avec un élément particulier doit produire l'affichage d'un texte particulier.

### 3. Programmer des interactions complexes

Utiliser une commande sur un élément doit pouvoir produire une variété d'effets, dont afficher un texte n'est qu'un exemple.

Implémenter une ou plusieurs des classes suivantes:

| Classe | Description |
|---|---|
| **MessageEffect** | Produit l'affichage d'un message dans la console. |
| **EndGameEffect** | Termine la partie en cours. |
| **RenameItemEffect** | Change le nom d'un élément donné de manière permanente. |
| **RemoveItemEffect** | Supprime un élément donné de la pièce dans laquelle il apparaît de manière permanente. |

- Chaque élément peut réagir à chaque commande en utilisant l'un des effets proposés ci-dessus (au lieu de simplement afficher un message comme précédemment demandé).
- BONUS: Chaque élément peut réagir à chaque commande en utilisant une série d'effets, au lieu d'un seul effet.

#### Exemples d'interactions à implémenter

- Manger le biscuit sur la table de la cuisine (`eat cookie`) doit produire sa disparition.
- Ouvrir le tiroir du bureau dans la chambre (`open drawer`) doit provoquer la réalisation que le tiroir est vide, et son nom doit changer en conséquence (`empty drawer`).
- Toucher une prise électrique (`touch plug`) doit produire la mort du héros, et donc la fin de la partie.

Si le bonus de l'étape 3 a été réalisé, chaque interaction doit être accompagnée d'au moins un message décrivant l'effet obtenu.
