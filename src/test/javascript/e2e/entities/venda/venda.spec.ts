import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { VendaComponentsPage, VendaDeleteDialog, VendaUpdatePage } from './venda.page-object';

const expect = chai.expect;

describe('Venda e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let vendaComponentsPage: VendaComponentsPage;
  let vendaUpdatePage: VendaUpdatePage;
  let vendaDeleteDialog: VendaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Vendas', async () => {
    await navBarPage.goToEntity('venda');
    vendaComponentsPage = new VendaComponentsPage();
    await browser.wait(ec.visibilityOf(vendaComponentsPage.title), 5000);
    expect(await vendaComponentsPage.getTitle()).to.eq('jhipsterCrudTestApplicationApp.venda.home.title');
    await browser.wait(ec.or(ec.visibilityOf(vendaComponentsPage.entities), ec.visibilityOf(vendaComponentsPage.noResult)), 1000);
  });

  it('should load create Venda page', async () => {
    await vendaComponentsPage.clickOnCreateButton();
    vendaUpdatePage = new VendaUpdatePage();
    expect(await vendaUpdatePage.getPageTitle()).to.eq('jhipsterCrudTestApplicationApp.venda.home.createOrEditLabel');
    await vendaUpdatePage.cancel();
  });

  it('should create and save Vendas', async () => {
    const nbButtonsBeforeCreate = await vendaComponentsPage.countDeleteButtons();

    await vendaComponentsPage.clickOnCreateButton();

    await promise.all([
      vendaUpdatePage.setLivroInput('livro'),
      vendaUpdatePage.setDataInput('2000-12-31'),
      vendaUpdatePage.setTotalInput('total'),
      vendaUpdatePage.clienteSelectLastOption()
    ]);

    expect(await vendaUpdatePage.getLivroInput()).to.eq('livro', 'Expected Livro value to be equals to livro');
    expect(await vendaUpdatePage.getDataInput()).to.eq('2000-12-31', 'Expected data value to be equals to 2000-12-31');
    expect(await vendaUpdatePage.getTotalInput()).to.eq('total', 'Expected Total value to be equals to total');

    await vendaUpdatePage.save();
    expect(await vendaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await vendaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Venda', async () => {
    const nbButtonsBeforeDelete = await vendaComponentsPage.countDeleteButtons();
    await vendaComponentsPage.clickOnLastDeleteButton();

    vendaDeleteDialog = new VendaDeleteDialog();
    expect(await vendaDeleteDialog.getDialogTitle()).to.eq('jhipsterCrudTestApplicationApp.venda.delete.question');
    await vendaDeleteDialog.clickOnConfirmButton();

    expect(await vendaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
