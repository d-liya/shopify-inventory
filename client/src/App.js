import { useEffect, useState } from "react";
const init = {
  name: "",
  inventoryReceived: 0,
  inventoryShipped: 0,
  inventoryOnHand: 0,
  id: 0,
};
function App() {
  const [productList, setProductList] = useState([]);
  const [modal, setModal] = useState("");
  const [product, setProduct] = useState(init);
  const fetchProducts = async () => {
    fetch("http://localhost:8080/api/productservice/products")
      .then((res) => res.json())
      .then((data) => setProductList(data))
      .catch((err) => alert(err));
  };
  useEffect(() => {
    fetchProducts();
  }, []);
  const handleOnPress = (type, id) => {
    if (type === "Add Product") {
      fetch("http://localhost:8080/api/productservice/products", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(product),
      })
        .then((res) => {
          console.log(res.status);
          setModal("");
          fetchProducts();
          setProduct(init);
        })
        .catch((err) => alert(err));
    } else {
      fetch("http://localhost:8080/api/productservice/products/" + product.id, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(product),
      })
        .then((res) => {
          setModal("");
          fetchProducts();
          setProduct(init);
        })
        .catch((err) => alert(err));
    }
  };
  const handleDelete = (id) => {
    fetch("http://localhost:8080/api/productservice/products/" + id, {
      method: "DELETE",
    })
      .then((res) => {
        fetchProducts();
      })
      .catch((err) => alert(err));
  };
  const handleDownload = () => {
    fetch("http://localhost:8080/api/productservice/download")
      .then((res) => {
        if (res.status === 200) {
          return res.blob();
        }
        alert("Error downloading file");
      })
      .then((blob) => {
        if (!blob) return;
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = "products.csv";
        a.click();
      })
      .catch((err) => alert(err));
  };

  return (
    <div className="flex items-center p-10 flex-1 min-h-screen flex-col">
      <header className="App-header">
        <h1 className="font-bold text-lg">Inventory Tracker</h1>
      </header>
      <main className="flex">
        <div className="flex flex-col items-center">
          <div className="flex justify-between">
            <button
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded float-right m-2"
              onClick={() => setModal("Add Product")}
            >
              Add Product
            </button>
            <button
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded float-right m-2"
              onClick={handleDownload}
            >
              Download CSV
            </button>
          </div>
          <table className="w-full border my-5">
            <thead>
              <tr>
                <th className="text-center px-4">Product Name</th>
                <th className="text-center px-4">Inventory Received</th>
                <th className="text-center px-4">Inventory Shipped</th>
                <th className="text-center px-4">Inventory On Hand</th>
                <th className="text-center px-4">Action</th>
              </tr>
            </thead>
            <tbody>
              {productList.map((p) => (
                <tr key={p.id}>
                  <td className="text-center px-4">{p.name}</td>
                  <td className="text-center px-4">{p.inventoryReceived}</td>
                  <td className="text-center px-4">{p.inventoryShipped}</td>
                  <td className="text-center px-4">{p.inventoryOnHand}</td>
                  <td className="text-center px-4">
                    <button
                      className="bg-indigo-400 px-1 text-white"
                      onClick={() => {
                        setProduct(p);
                        setModal("Edit Product");
                      }}
                    >
                      Update
                    </button>
                    <button
                      className="bg-red-400 px-1 text-white"
                      onClick={() => handleDelete(p.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        {modal && (
          <div className="fixed top-1/3 left-1/3 bg-slate-100 p-5 w-96">
            <div>
              <h2 className="text-center">{modal}</h2>
              <div className="flex flex-col">
                <label className="text-gray-700">Product Name</label>
                <input
                  className="border border-gray-400 p-2 w-full"
                  type="text"
                  value={product.name}
                  onChange={(e) =>
                    setProduct({ ...product, name: e.target.value })
                  }
                />
                <label className="text-gray-700">Inventory Received</label>
                <input
                  className="border border-gray-400 p-2 w-full"
                  type="text"
                  value={product.inventoryReceived}
                  onChange={(e) =>
                    setProduct({
                      ...product,
                      inventoryReceived: e.target.value,
                    })
                  }
                />
                <label className="text-gray-700">Inventory Shipped</label>
                <input
                  className="border border-gray-400 p-2 w-full"
                  type="text"
                  value={product.inventoryShipped}
                  onChange={(e) =>
                    setProduct({ ...product, inventoryShipped: e.target.value })
                  }
                />
                <label className="text-gray-700">Inventory On Hand</label>
                <input
                  className="border border-gray-400 p-2 w-full"
                  type="text"
                  value={product.inventoryOnHand}
                  onChange={(e) =>
                    setProduct({ ...product, inventoryOnHand: e.target.value })
                  }
                />
              </div>
              <div className="flex justify-between py-4">
                <button
                  className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded float-right"
                  onClick={() => handleOnPress(modal)}
                >
                  {modal}
                </button>
                <button
                  className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded float-right"
                  onClick={() => {
                    setModal("");
                    setProduct(init);
                  }}
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        )}
      </main>
    </div>
  );
}

export default App;
