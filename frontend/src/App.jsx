
import './App.css';
import ReporteClientesPorcinos from './ReporteClientesPorcinos';
import ClientesCRUD from './ClientesCRUD';
import AlimentacionCRUD from './AlimentacionCRUD';
import PorcinosCRUD from './PorcinosCRUD';


import { useRef } from 'react';

function App() {
  const porcinosRef = useRef();

  // Función para recargar listas en PorcinosCRUD
  const recargarListasPorcinos = () => {
    if (porcinosRef.current && porcinosRef.current.recargarListas) {
      porcinosRef.current.recargarListas();
    }
  };

  return (
    <div>
      <h1>Granja S.A.</h1>
      <section>
        <ClientesCRUD onDataChange={recargarListasPorcinos} />
      </section>
      <section>
        <AlimentacionCRUD onDataChange={recargarListasPorcinos} />
      </section>
      <section>
        <PorcinosCRUD ref={porcinosRef} />
      </section>
      <section>
        <ReporteClientesPorcinos />
      </section>
    </div>
  );
}

export default App
