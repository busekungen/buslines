async function fetchBusLines() {
    try {
        const response = await fetch('/buslines');
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        const busLinesData = await response.json();
        return busLinesData;
    } catch (error) {
        console.error('Error fetching data:', error);
        return [];
    }
}

function createStopsList(stops) {
    const stopsList = document.createElement('ul');
    stopsList.className = 'stopsList';
    stops.forEach(stop => {
        const stopItem = document.createElement('li');
        stopItem.innerHTML = `${stop.name} <small>(${stop.number})</small>`;
        stopsList.appendChild(stopItem);
    });
    return stopsList;
}

async function displayBusLines() {
    const busLinesDiv = document.getElementById('busLines');

    const busLinesData = await fetchBusLines();

    busLinesData.forEach(busLine => {
        const lineDiv = document.createElement('div');
        lineDiv.className = 'line';

        const lineHeader = document.createElement('h2');
        lineHeader.textContent = `Line ${busLine.lineNr} (${busLine.stops.length} stops)`;
        lineHeader.addEventListener('click', function () {
            const stopsList = this.nextElementSibling;
            stopsList.classList.toggle('active');
        });

        const stopsList = createStopsList(busLine.stops);
        lineDiv.appendChild(lineHeader);
        lineDiv.appendChild(stopsList);
        busLinesDiv.appendChild(lineDiv);
    });
}

window.onload = displayBusLines;
