# Fluffy Broccoli

## Event specification

```json
event : {
	userId: idInTheGamifiedApp,
	timestamp : 2018-12-17:17-00-00,
	type: drink,
	properties: {
		type: beer,
		quantity: some
	}
}
```

### Rules specification (without state)

```json
rule : {
	if: {
		type: drink,
        properties: {
            type: beer,
            quantity: some
        }
	},
	then : {
		awardBadge : /badges/champion,
		awardPoints : {
			pointScale : /pointScales/health,
			amount: 1000
		}
	}
}
```

