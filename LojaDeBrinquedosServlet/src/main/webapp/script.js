function btnExcluirBrinquedo(button) {
	// Encontra o elemento TR pai do botão clicado
	var tr = button.closest('tr');

	// Encontra a célula TD com o conteúdo desejado
	var tdId = tr.querySelector('td:first-child'); // A primeira célula na mesma linha

	// Captura o conteúdo da célula TD
	var conteudoId = tdId.textContent.trim(); // Remove espaços em branco

	// Exibe o modal de confirmação
	$('#confirmacaoExclusaoModal').modal('show');

	// Configura o botão "Confirmar" do modal para executar a exclusão
	$('#btn-confirmar-exclusao').on('click', function() {
		// lógica de exclusão

		// Cria um input com o id
		var inputID = document.createElement("input");
		inputID.type = "number";
		inputID.name = "txtCodigo";
		inputID.value = conteudoId;

		// Cria um formulário oculto
		var form = document.createElement("form");
		form.action = "ServletBrinquedo?cmd=excluir"; // URL do servlet
		form.method = "post"; // Define o método POST

		// Adiciona o campo de entrada ao formulário
		form.appendChild(inputID);

		// Adiciona o formulário ao corpo do documento (pode ser oculto)
		document.body.appendChild(form);

		// Envia o formulário
		form.submit();
		// Fecha o modal
		$('#confirmacaoExclusaoModal').modal('hide');

	});

}

function btnEditarBrinquedo(button) {
	// Encontra o elemento TR pai do botão clicado
	var tr = button.closest('tr');

	// Encontra a célula TD com o conteúdo desejado
	var tdId = tr.querySelector('td:first-child'); // A primeira célula na mesma linha

	// Captura o conteúdo da célula TD
	var conteudoId = tdId.textContent.trim(); // Remove espaços em branco

	// Cria um input com o id
	var inputID = document.createElement("input");
	inputID.type = "number";
	inputID.name = "txtCodigo";
	inputID.value = conteudoId;

	// Cria um formulário oculto
	var form = document.createElement("form");
	form.action = "ServletBrinquedo?cmd=atu"; // URL do servlet
	form.method = "post"; // Define o método POST

	// Adiciona o campo de entrada ao formulário
	form.appendChild(inputID);

	// Adiciona o formulário ao corpo do documento (pode ser oculto)
	document.body.appendChild(form);

	// Envia o formulário
	form.submit();
}

function btnExcluirCategoria(button) {
	// Encontra o elemento TR pai do botão clicado
	var tr = button.closest('tr');

	// Encontra a célula TD com o conteúdo desejado
	var tdId = tr.querySelector('td:first-child'); // A primeira célula na mesma linha

	// Captura o conteúdo da célula TD
	var conteudoId = tdId.textContent.trim(); // Remove espaços em branco

	// Exibe o modal de confirmação
	$('#confirmacaoExclusaoModal').modal('show');

	// Configura o botão "Confirmar" do modal para executar a exclusão
	$('#btn-confirmar-exclusao').on('click', function() {
		// lógica de exclusão

		// Cria um input com o id
		var inputID = document.createElement("input");
		inputID.type = "number";
		inputID.name = "txtCodigo";
		inputID.value = conteudoId;

		// Cria um formulário oculto
		var form = document.createElement("form");
		form.action = "ServletCategoria?cmd=excluir"; // URL do servlet
		form.method = "post"; // Define o método POST

		// Adiciona o campo de entrada ao formulário
		form.appendChild(inputID);

		// Adiciona o formulário ao corpo do documento (pode ser oculto)
		document.body.appendChild(form);

		// Envia o formulário
		form.submit();
		// Fecha o modal
		$('#confirmacaoExclusaoModal').modal('hide');

	});

}

function btnEditarCategoria(button) {
	// Encontra o elemento TR pai do botão clicado
	var tr = button.closest('tr');

	// Encontra a célula TD com o conteúdo desejado
	var tdId = tr.querySelector('td:first-child'); // A primeira célula na mesma linha

	// Captura o conteúdo da célula TD
	var conteudoId = tdId.textContent.trim(); // Remove espaços em branco

	// Cria um input com o id
	var inputID = document.createElement("input");
	inputID.type = "number";
	inputID.name = "txtCodigo";
	inputID.value = conteudoId;

	// Cria um formulário oculto
	var form = document.createElement("form");
	form.action = "ServletCategoria?cmd=atu"; // URL do servlet
	form.method = "post"; // Define o método POST

	// Adiciona o campo de entrada ao formulário
	form.appendChild(inputID);

	// Adiciona o formulário ao corpo do documento (pode ser oculto)
	document.body.appendChild(form);

	// Envia o formulário
	form.submit();
}

function btnExcluirUsuario(button) {
	// Encontra o elemento TR pai do botão clicado
	var tr = button.closest('tr');

	// Encontra a célula TD com o conteúdo desejado
	var tdId = tr.querySelector('td:first-child'); // A primeira célula na mesma linha

	// Captura o conteúdo da célula TD
	var conteudoId = tdId.textContent.trim(); // Remove espaços em branco

	// Exibe o modal de confirmação
	$('#confirmacaoExclusaoModal').modal('show');

	// Configura o botão "Confirmar" do modal para executar a exclusão
	$('#btn-confirmar-exclusao').on('click', function() {
		// lógica de exclusão

		// Cria um input com o id
		var inputID = document.createElement("input");
		inputID.type = "number";
		inputID.name = "txtId";
		inputID.value = conteudoId;

		// Cria um formulário oculto
		var form = document.createElement("form");
		form.action = "ServletUser?cmd=excluir"; // URL do servlet
		form.method = "post"; // Define o método POST

		// Adiciona o campo de entrada ao formulário
		form.appendChild(inputID);

		// Adiciona o formulário ao corpo do documento (pode ser oculto)
		document.body.appendChild(form);

		// Envia o formulário
		form.submit();
		// Fecha o modal
		$('#confirmacaoExclusaoModal').modal('hide');

	});

}

function btnEditarUsuario(button) {
	// Encontra o elemento TR pai do botão clicado
	var tr = button.closest('tr');

	// Encontra a célula TD com o conteúdo desejado
	var tdId = tr.querySelector('td:first-child'); // A primeira célula na mesma linha

	// Captura o conteúdo da célula TD
	var conteudoId = tdId.textContent.trim(); // Remove espaços em branco

	// Cria um input com o id
	var inputID = document.createElement("input");
	inputID.type = "number";
	inputID.name = "txtId";
	inputID.value = conteudoId;

	// Cria um formulário oculto
	var form = document.createElement("form");
	form.action = "ServletUser?cmd=atu"; // URL do servlet
	form.method = "post"; // Define o método POST

	// Adiciona o campo de entrada ao formulário
	form.appendChild(inputID);

	// Adiciona o formulário ao corpo do documento (pode ser oculto)
	document.body.appendChild(form);

	// Envia o formulário
	form.submit();
}

function validateFileType(){
        var file = document.getElementById("file").value;
        var idxDot = file.lastIndexOf(".") + 1;
        var extFile = file.substr(idxDot, file.length).toLowerCase();
        if (extFile=="jpg" || extFile=="jpeg" || extFile=="png" || extFile=="svg" || extFile=="bmp"){
			
        }else{
			document.getElementById("file").value = null;
            alert("Apenas arquivos jpg, jpeg, png, svg e bmp são aceitos!");
        }   
    }