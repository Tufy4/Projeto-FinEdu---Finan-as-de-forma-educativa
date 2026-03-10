/**
* Inicializa o gráfico de comparação de investimentos
* @param {Object} evolucaoAtivo 
* @param {Object} evolucaoPoupanca
*/
function inicializarGrafico(evolucaoAtivo, evolucaoPoupanca) {
	const ctx = document.getElementById('chartEvolucao').getContext('2d');

	const labels = Object.keys(evolucaoAtivo).map(m => 'Mês ' + m);

	new Chart(ctx, {
		type: 'line',
		data: {
			labels: labels,
			datasets: [
				{
					label: 'Ativo Selecionado',
					data: Object.values(evolucaoAtivo),
					borderColor: '#28a745',
					backgroundColor: 'rgba(40, 167, 69, 0.1)',
					fill: true,
					tension: 0.4,
					borderWidth: 3,
					pointRadius: 0
				},
				{
					label: 'Poupança (Referência)',
					data: Object.values(evolucaoPoupanca),
					borderColor: '#6c757d',
					borderDash: [5, 5],
					fill: false,
					tension: 0.4,
					borderWidth: 2,
					pointRadius: 0
				}
			]
		},
		options: {
			responsive: true,
			maintainAspectRatio: false,
			plugins: {
				legend: { position: 'top', align: 'end' }
			},
			scales: {
				y: {
					ticks: { callback: (val) => 'R$ ' + val.toLocaleString('pt-BR') },
					grid: { color: '#f0f0f0' }
				},
				x: { grid: { display: false } }
			}
		}
	});
}


function converterMoedas() {
	const brl = document.getElementById('valorBRL').value;
	const resUSD = document.getElementById('resUSD');
	const resEUR = document.getElementById('resEUR');

	if (brl > 0) {
		const usd = (brl / window.taxaUSDReal).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
		const eur = (brl / window.taxaEURReal).toLocaleString('de-DE', { minimumFractionDigits: 2, maximumFractionDigits: 2 });

		resUSD.innerText = '$ ' + usd;
		resEUR.innerText = '€ ' + eur;
	} else {
		resUSD.innerText = '$ 0.00';
		resEUR.innerText = '€ 0.00';
	}
}