# Fluffy Broccoli

## Proposition d'architecture
### specification DTO

```
UserDTO :
- username : String
- reputation : double
- badges : List<BadgeDTO>
- points : List<UserPointScaleDTO>
```

```
LeaderboardDTO :
- leaderboard : List<UserDTO> # 50 utilisateurs trié par la reputation
```

```
BadgeDTO :
- name : String
- description : String
- icon : String
```

```
UserPointScaleDTO :
- name : String           # Nom du pointscale (à ajouter dans la DB)
- points : double         # Addition des points de l'utilisateur sur le pointscale
```

### Modification sur la DB : 
- Retirer first_name et last_name de la table User
- User.id devient l'id pour les traitement dans la db interne, username est mappé à l'id de l'userEntity utilisé par le service externe (UUID)
- Ajouter name à la table PointScale (nom de l'échelle)
- Ajout de la réputation pour l'utilisateur. Valeur calculé par rapport au points et badges (1 point = 1 réputation, 1 badge = 10 réputation)


## Event specification

```json
{
  "event": {
    "userId": "idInTheGamifiedApp",
    "timestamp": "2018-12-17:17-00-00",
    "type": "drink",
    "properties": {
      "type": "beer",
      "quantity": "some"
    }
  }
}

```

## Rules specification (without state)

```json
{
  "rule": {
    "if": {
      "type": "drink",
      "properties": {
        "type": "beer",
        "quantity": "some"
      }
    },
    "then": {
      "awardBadge": "/badges/champion",
      "awardPoints": {
        "pointScale": "/pointScales/health",
        "amount": 1000
      }
    }
  }
}
```

