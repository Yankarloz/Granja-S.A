
import './App.css';
import ReporteClientesPorcinos from './ReporteClientesPorcinos';
import ClientesCRUD from './ClientesCRUD';
import AlimentacionCRUD from './AlimentacionCRUD';
import PorcinosCRUD from './PorcinosCRUD';


function App() {
  return (
    <div>
      <h1>Granja S.A.</h1>
      <section>
        <ClientesCRUD />
      </section>
      <section>
        <AlimentacionCRUD />
      </section>
      <section>
        <PorcinosCRUD />
      </section>
      <section>
        <ReporteClientesPorcinos />
      </section>
    </div>
  );
}

export default App
