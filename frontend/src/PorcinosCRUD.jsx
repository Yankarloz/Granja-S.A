import { useEffect, useState, forwardRef, useImperativeHandle } from "react";

const PorcinosCRUD = forwardRef((props, ref) => {
  const [porcinos, setPorcinos] = useState([]);
  const [clientes, setClientes] = useState([]);
  const [alimentos, setAlimentos] = useState([]);
  const [form, setForm] = useState({
    identificacion: "",
    raza: "york",
    edad: "",
    peso: "",
    clienteCedula: "",
    alimentacionId: "",
  });
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);

  const fetchAll = () => {
    fetch("http://localhost:8080/porcinos").then((r) => r.json()).then(setPorcinos);
    fetch("http://localhost:8080/clientes").then((r) => r.json()).then(setClientes);
    fetch("http://localhost:8080/alimentacion").then((r) => r.json()).then(setAlimentos);
  };

  useEffect(() => {
    fetchAll();
  }, []);

  // Exponer función recargarListas al padre
  useImperativeHandle(ref, () => ({
    recargarListas: fetchAll
  }));

  const handleChange = (e) => {
    const { name, value, type, selectedOptions } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validar que los campos requeridos no sean solo espacios en blanco
    if (
      !form.identificacion.trim() ||
      !form.raza.trim() ||
      !form.edad.toString().trim() ||
      !form.peso.toString().trim() ||
      !form.clienteCedula.trim() ||
      !form.alimentacionId.toString().trim()
    ) {
      setError("No se permiten campos vacíos o solo espacios en blanco en los campos requeridos.");
      return;
    }
    const method = editId ? "PUT" : "POST";
    const url = editId ? `http://localhost:8080/porcinos/${editId}` : "http://localhost:8080/porcinos";
    // Adaptar el objeto para el backend
    const body = {
      identificacion: form.identificacion,
      raza: form.raza,
      edad: Number(form.edad),
      peso: Number(form.peso),
      cliente: form.clienteCedula ? { cedula: form.clienteCedula } : null,
      alimentaciones: form.alimentacionId ? [{ id: Number(form.alimentacionId) }] : [],
    };
    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    })
      .then(() => {
        setForm({ identificacion: "", raza: "york", edad: "", peso: "", clienteCedula: "", alimentacionId: "" });
        setEditId(null);
        setError(null);
        fetchAll();
      })
      .catch(() => setError("Error al guardar porcino"));
  };

  const handleEdit = (porcino) => {
    setForm({
      identificacion: porcino.identificacion,
      raza: porcino.raza,
      edad: porcino.edad,
      peso: porcino.peso,
      clienteCedula: porcino.cliente ? porcino.cliente.cedula : "",
      alimentacionId: porcino.alimentaciones && porcino.alimentaciones.length > 0 ? porcino.alimentaciones[0].id : "",
    });
    setEditId(porcino.id);
  };

  const handleDelete = (id) => {
    if (!window.confirm("¿Eliminar porcino?")) return;
    fetch(`http://localhost:8080/porcinos/${id}`, { method: "DELETE" })
      .then(fetchAll)
      .catch(() => setError("Error al eliminar porcino"));
  };

  return (
    <div>
      <h2>Porcinos</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit} style={{ marginBottom: 20 }}>
        <input name="identificacion" placeholder="Identificación" value={form.identificacion} onChange={handleChange} required disabled={!!editId} />
        <select name="raza" value={form.raza} onChange={handleChange} required>
          <option value="york">York</option>
          <option value="hamp">Hamp</option>
          <option value="duroc">Duroc</option>
        </select>
        <input name="edad" placeholder="Edad" type="number" value={form.edad} onChange={handleChange} required />
        <input name="peso" placeholder="Peso" type="number" step="0.01" value={form.peso} onChange={handleChange} required />
        <select name="clienteCedula" value={form.clienteCedula} onChange={handleChange} required>
          <option value="">Seleccione cliente</option>
          {clientes.map((c) => (
            <option key={c.cedula} value={c.cedula}>{c.nombres} {c.apellidos}</option>
          ))}
        </select>
        <select name="alimentacionId" value={form.alimentacionId} onChange={handleChange}>
          <option value="">Seleccione alimentación</option>
          {alimentos.map((a) => (
            <option key={a.id} value={a.id}>{a.descripcion} ({a.dosis})</option>
          ))}
        </select>
        <button type="submit">{editId ? "Actualizar" : "Agregar"}</button>
  {editId && <button type="button" onClick={() => { setForm({ identificacion: "", raza: "york", edad: "", peso: "", clienteCedula: "", alimentacionId: "" }); setEditId(null); }}>Cancelar</button>}
      </form>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>ID</th>
            <th>Identificación</th>
            <th>Raza</th>
            <th>Edad</th>
            <th>Peso</th>
            <th>Cliente</th>
            <th>Alimentaciones</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {porcinos.map((p) => (
            <tr key={p.id}>
              <td>{p.id}</td>
              <td>{p.identificacion}</td>
              <td>{p.raza}</td>
              <td>{p.edad}</td>
              <td>{p.peso}</td>
              <td>{p.cliente ? `${p.cliente.nombres} ${p.cliente.apellidos}` : ""}</td>
              <td>{p.alimentaciones && p.alimentaciones.map((a) => a.descripcion).join(", ")}</td>
              <td>
                <button onClick={() => handleEdit(p)}>Editar</button>
                <button onClick={() => handleDelete(p.id)}>Eliminar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
});

export default PorcinosCRUD;
