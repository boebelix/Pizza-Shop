class State {

	constructor(name, hasToBeLoggedIn) {
		this.name = name;
		this.hasToBeLoggedIn = hasToBeLoggedIn;
	}

}

const getStateByName = (name) => {
	for (let i = 0; i < STATES.length; i++) {
		if (STATES[i].name === name) {
			return STATES[i];
		}
	}
	return getStateByName(STATE_MENU);
}

