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
    "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "type": "drink",
    "properties": {
      "somekey1": "somevalue1",
      "somekey2": "somevalue2",
      "somekey3": "somevalue3"
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
        "somekey1": "somevalue1",
        "somekey2": "somevalue2",
        "somekey3": "somevalue3"
      }
    },
    "then": {
      "award_badge": {
        "badge_id": 8
      },
      "award_points": {
        "point_scale_id": 12,
        "amount": 1000
      }
    }
  }
}
```
