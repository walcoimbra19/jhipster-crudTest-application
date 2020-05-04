import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { LivroComponentsPage, LivroDeleteDialog, LivroUpdatePage } from './livro.page-object';

const expect = chai.expect;

describe('Livro e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let livroComponentsPage: LivroComponentsPage;
  let livroUpdatePage: LivroUpdatePage;
  let livroDeleteDialog: LivroDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Livros', async () => {
    await navBarPage.goToEntity('livro');
    livroComponentsPage = new LivroComponentsPage();
    await browser.wait(ec.visibilityOf(livroComponentsPage.title), 5000);
    expect(await livroComponentsPage.getTitle()).to.eq('jhipsterCrudTestApplicationApp.livro.home.title');
    await browser.wait(ec.or(ec.visibilityOf(livroComponentsPage.entities), ec.visibilityOf(livroComponentsPage.noResult)), 1000);
  });

  it('should load create Livro page', async () => {
    await livroComponentsPage.clickOnCreateButton();
    livroUpdatePage = new LivroUpdatePage();
    expect(await livroUpdatePage.getPageTitle()).to.eq('jhipsterCrudTestApplicationApp.livro.home.createOrEditLabel');
    await livroUpdatePage.cancel();
  });

  it('should create and save Livros', async () => {
    const nbButtonsBeforeCreate = await livroComponentsPage.countDeleteButtons();

    await livroComponentsPage.clickOnCreateButton();

    await promise.all([
      livroUpdatePage.setTituloInput('titulo'),
      livroUpdatePage.setDescricaoInput('descricao'),
      livroUpdatePage.setPrecoInput('5'),
      livroUpdatePage.autorSelectLastOption()
    ]);

    expect(await livroUpdatePage.getTituloInput()).to.eq('titulo', 'Expected Titulo value to be equals to titulo');
    expect(await livroUpdatePage.getDescricaoInput()).to.eq('descricao', 'Expected Descricao value to be equals to descricao');
    expect(await livroUpdatePage.getPrecoInput()).to.eq('5', 'Expected preco value to be equals to 5');

    await livroUpdatePage.save();
    expect(await livroUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await livroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Livro', async () => {
    const nbButtonsBeforeDelete = await livroComponentsPage.countDeleteButtons();
    await livroComponentsPage.clickOnLastDeleteButton();

    livroDeleteDialog = new LivroDeleteDialog();
    expect(await livroDeleteDialog.getDialogTitle()).to.eq('jhipsterCrudTestApplicationApp.livro.delete.question');
    await livroDeleteDialog.clickOnConfirmButton();

    expect(await livroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
