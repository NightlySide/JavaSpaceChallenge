# Space Challenge

> Projet Java conçut par :
>
> - [Erwan AUBRY]()
> - [Alexandre FROEHLICH](https://nightlyside.github.io/)
>
> Dans le cadre des cours de Programmation Avancée et orienté objet en JAVA de l'ENSTA Bretagne

## Introduction

Dans le cadre de la formation [SNS](https://www.ensta-bretagne.fr/fr/systemes-numeriques-et-securite-0) (Systèmes Numériques et Sécurité) et [MASSEL](https://www.ensta-bretagne.fr/fr/master-architecture-et-securite-des-systemes-electroniques-et-logiciels) (Master Architecture et Sécurité des Systèmes Electroniques et Logiciels) et plus précisément dans le cadre du cours de programmation avancé en Java dispensé à l'ENSTA Bretagne, nous avons réalisé un projet sur un thème imposé : ***Space Challenge***.

<img src="./images/space_challenge_logo.png" alt="Space Challenge Logo" style="zoom: 50%;" />

<center><em>Image descriptive donnée dans l'énoncé du projet</em></center>

Ce projet est tout d'abord une introduction à la programmation orientée objet avancée et à pour but de familiariser les étudiants avec de nouveaux principes de programmations tels que les patterns ou les interfaces. **Le but final de ce projet est de concevoir un outil permettant d'aider à la décision par rapport au choix des fusées à envoyer pour réduire le coût de la mission.**

Ce projet se déroule en 3 parties qui décrivent une construction incrémentale du projet :

1. La première partie se concentre sur la **construction des classes** et des objets nécessaires à la simulation que nous allons effectuer dans le projet. Il s'agira de créer des classes, des interfaces afin d'initialiser la structure du projet.
2. La seconde partie se concentre sur un **lancement simpliste d'une simulation de programme spatial**. Il s'agit dans ce contexte d'utiliser les classes précédemment crées afin de s'habituer à les utiliser pour la 3ème partie.
3. La dernière partie est sans doute la plus longue. Une fois le projet de base terminé, nous devrons prendre des initiatives pour **concevoir un logiciel d'aide à la décision**. Cette partie n'est plus guidée et nous devrons faire des choix concernant les paramètres à faire varier pour réduire le coût de la mission en général.

## PART 1: The design

La première partie est rapide. Il s'agit de comprendre le sujet puis de traduire les différents éléments nécessaire à la simulation en classes et en objets. Voici les objets que nous relevons pour la simulation :

- **Rocket** (U1, U2) : les fusées à envoyer
- **Item** : les objets à transporter

Il est assez évident de se rendre compte que pour généraliser le code il sera nécessaire d'utiliser l'héritage de classe que nous fournit le langage de programmation. Avant de mettre les mains dans le cambouis et de coder les classes, il est important de rédiger un diagramme de classes afin de vérifier que l'on ai négligé aucun point du projet. 

![Class diagram of the part 1 : design](./class_diagram.drawio.png)

<center><em>Diagramme de classes de la simulation</em></center>

Pour la partie programmation il y a peu de commentaires à faire comme les étudiants sont très guidés dans les choix à faire. On a une liste d'étapes à suivre pas à pas pour avoir une base commune de code entre tous les étudiants.

### Test unit avec JUnit5

Afin de s'assurer que le logiciel que nous allons concevoir fonctionne correctement à n'importe quelle étape de son développement, nous allons mettre en place une suite de tests unitaires (*Test unit* en anglais). Nous implémentons ces tests unitaires à l'aide de [JUnit5](https://junit.org/junit5/) et nous allons chercher à valider plusieurs points :

- Est-ce qu'un objet peut être chargé dans une fusée ? Est-ce que le poids de la fusée est bien modifié ? Est-ce qu'on peut le charger si on a plus de place dans la fusée ?
- Est-ce que lorsqu'on crée un objet du type *Rocket*, *Simulation* ou *Item* est-ce que les instances sont du bon type ? Est-ce que *U1* et *U2* sont bien des sous-classes de *Rocket* ? Est-ce que le changement d'attribut est fonctionnel ?
- Dans un second temps (cf. PART 3) nous chercherons aussi à vérifier que la probabilité de crash des fusées rentre bien dans les critères de distribution de probabilité.

## PART 2: The Simulation



## PART 3: Building a decision-making tool



