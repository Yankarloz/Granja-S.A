import './App.css';
import ReporteClientesPorcinos from './ReporteClientesPorcinos';
import ClientesCRUD from './ClientesCRUD';
import AlimentacionCRUD from './AlimentacionCRUD';
import PorcinosCRUD from './PorcinosCRUD';

function App() {
  return (
    <div>
      <h1>La Granja S.A.</h1>
    <ClientesCRUD />
    <hr />
    <AlimentacionCRUD />
    <hr />
    <PorcinosCRUD />
    <hr />
    <ReporteClientesPorcinos />
    </div>
  );
}

export default App
