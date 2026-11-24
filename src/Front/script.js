const catalogoProdutos = [
  {
    id: 1,
    nome: 'Chaveiro / Mini Amigurumi',
    descricao: 'Mini peça de crochê artesanal ideal para bolsas e chaves, feita com fio 100% algodão.',
    preco: 25.0,
    categoria: 'Mais vendido',
    imagem: 'image_6d6f9c.jpg'
  },
  {
    id: 2,
    nome: 'Bichinho Pronta Entrega',
    descricao: 'Amigurumi de tamanho médio padrão, macio, seguro e antialérgico para crianças.',
    preco: 45.0,
    categoria: 'Pronta entrega',
    imagem: 'image_6d6f00.jpg'
  },
  {
    id: 3,
    nome: 'Encomenda Personalizada',
    descricao: 'Peça exclusiva feita sob medida conforme as cores e tamanho escolhidos pelo cliente.',
    preco: 70.0,
    categoria: 'Sob medida',
    imagem: 'image_6d6ec3.jpg'
  },
  {
    id: 4,
    nome: 'Kit Presente Dueto',
    descricao: 'Conjunto especial contendo um bichinho médio e um chaveiro combinando.',
    preco: 110.0,
    categoria: 'Kit promocional',
    imagem: 'image_6d6f3e.jpg'
  },
  {
    id: 5,
    nome: 'Kit Colecionável (3 Minis)',
    descricao: 'Família de mini bichinhos artesanais em miniatura para colecionar ou decorar.',
    preco: 95.0,
    categoria: 'Kit especial',
    imagem: 'image_6d6ee1.jpg'
  }
];

let carrinho = [];
let usuarioLogado = null;
const FRETE_FIXO = 15.0;

function formatarMoeda(valor) {
  return valor.toLocaleString('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  });
}

function fazerLogin(event) {
  event.preventDefault();

  const nome = document.getElementById('nome-login').value.trim();
  const telefone = document.getElementById('telefone-login').value.trim();
  const email = document.getElementById('email-login').value.trim();
  const senha = document.getElementById('senha-login').value.trim();

  if (!nome || !telefone || !email || !senha) {
    alert('Preencha todos os campos para entrar na loja.');
    return;
  }

  usuarioLogado = nome;
  document.getElementById('nome-usuario-logado').textContent = `👤 ${nome}`;

  document.getElementById('tela-login').classList.add('oculto');
  document.getElementById('sistema-loja').classList.remove('oculto');

  renderizarCatalogo();
  atualizarCarrinhoUI();
}

function fazerLogout() {
  usuarioLogado = null;
  carrinho = [];

  document.getElementById('form-login').reset();
  document.getElementById('sistema-loja').classList.add('oculto');
  document.getElementById('tela-login').classList.remove('oculto');
  atualizarCarrinhoUI();
}

function renderizarCatalogo() {
  const container = document.getElementById('grid-produtos');

  container.innerHTML = catalogoProdutos.map((produto) => `
    <article class="card-produto">
      <img src="${produto.imagem}" alt="${produto.nome}" />
      <div class="card-content">
        <div>
          <span class="tag">${produto.categoria}</span>
          <h3>${produto.nome}</h3>
          <p>${produto.descricao}</p>
        </div>

        <div class="card-footer">
          <span class="preco">${formatarMoeda(produto.preco)}</span>
          <button class="btn-adicionar" type="button" onclick="adicionarAoCarrinho(${produto.id})">+ Adicionar</button>
        </div>
      </div>
    </article>
  `).join('');
}

function adicionarAoCarrinho(idProduto) {
  const produto = catalogoProdutos.find((item) => item.id === idProduto);

  if (!produto) return;

  const itemExistente = carrinho.find((item) => item.produto.id === idProduto);

  if (itemExistente) {
    itemExistente.quantidade += 1;
  } else {
    carrinho.push({ produto, quantidade: 1 });
  }

  atualizarCarrinhoUI();
}

function alterarQuantidade(idProduto, delta) {
  const item = carrinho.find((produto) => produto.produto.id === idProduto);

  if (!item) return;

  item.quantidade += delta;

  if (item.quantidade <= 0) {
    carrinho = carrinho.filter((produto) => produto.produto.id !== idProduto);
  }

  atualizarCarrinhoUI();
}

function limparCarrinho() {
  carrinho = [];
  atualizarCarrinhoUI();
}

function atualizarCarrinhoUI() {
  const lista = document.getElementById('lista-carrinho');

  if (carrinho.length === 0) {
    lista.innerHTML = `<p class="carrinho-vazio">Seu carrinho está vazio.</p>`;
  } else {
    lista.innerHTML = carrinho.map(item => `
      <div class="item-carrinho">
        <div class="info">
          <h4>${item.produto.nome}</h4>
          <span>${formatarMoeda(item.produto.preco * item.quantidade)}</span>
        </div>

        <div class="qtd-controles">
          <button type="button" onclick="alterarQuantidade(${item.produto.id}, -1)">-</button>
          <span>${item.quantidade}</span>
          <button type="button" onclick="alterarQuantidade(${item.produto.id}, 1)">+</button>
        </div>
      </div>
    `).join('');
  }

  const subtotal = carrinho.reduce((total, item) => total + item.produto.preco * item.quantidade, 0);
  const frete = subtotal > 0 ? FRETE_FIXO : 0;
  const totalGeral = subtotal + frete;

  document.getElementById('subtotal-val').textContent = formatarMoeda(subtotal);
  document.getElementById('frete-val').textContent = formatarMoeda(frete);
  document.getElementById('total-val').textContent = formatarMoeda(totalGeral);
}

function finalizarCompra() {
  if (carrinho.length === 0) {
    alert('Seu carrinho está vazio. Adicione itens antes de confirmar a compra.');
    return;
  }

  const subtotal = carrinho.reduce((total, item) => total + item.produto.preco * item.quantidade, 0);
  const total = subtotal + FRETE_FIXO;

  alert(`Pedido confirmado com sucesso!\n\nCliente: ${usuarioLogado || 'Cliente'}\nTotal: ${formatarMoeda(total)}`);

  carrinho = [];
  atualizarCarrinhoUI();
}

document.getElementById('form-login').addEventListener('submit', fazerLogin);

renderizarCatalogo();
atualizarCarrinhoUI();
