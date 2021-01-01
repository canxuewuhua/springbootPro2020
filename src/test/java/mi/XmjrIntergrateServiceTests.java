package mi;


import com.example.demo.SpringBootWildApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 *  小米联调单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
@WebAppConfiguration
public class XmjrIntergrateServiceTests {

    @Before
    public void setUp() {
    }

    private String customerCode = "1002506738";
    private String bankNo = "6222021703006960544";

    /**
     * 第一步、授信准入正向流程测试(准入通过)
     * ①插入准入流水 lm_credit_enable_record
     * ②发起小米准入接口
     * ②回写准入流水
     *
     */
    @Test
    //    @Rollback
    public void applyCreditEnableTest() {
//        String key = redisUtilService.getRedisKey(ConstRedisParam.CUSTOMER_PARTNER_ACCESS + customerCode + ":" + EnumPartnerCode.PARTNER_XMJR_API);
//        redisUtilService.del(key);
//        // 登录用户，查询用户信息
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ResultDTO resultDTO = creditXmjrService.applyCreditEnable(customerVO, partnerVO);
//        Assert.assertEquals(true, resultDTO.checkSuccess());
//        redisUtilService.del(key);
    }

    /**
     * 第二步 授信注册正向流程测试,未进行过注册-->(注册通过)
     * ①插入客户商户注册记录表lm_customer_register_record
     * 小米金融没有联合注册接口 将客户编码作为openId
     * ③回写联合注册表和准入流水表
     */
    @Test
//    @Rollback
    public void registerTest() {
//        String key = redisUtilService.getRedisKey(ConstRedisParam.CUSTOMER_PARTNER_ACCESS + customerCode + ":" + EnumPartnerCode.PARTNER_XMJR_API);
//        redisUtilService.del(key);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ResultDTO resultDTO = creditXmjrService.register(customerVO, partnerVO);
//        Assert.assertEquals(true, resultDTO.checkSuccess());
//        redisUtilService.del(key);
    }

    /**
     * 第三步、获取协议列表 CREDIT(授信阶段)
     * 协议查询时 CREDIT(授信阶段)、BANKCARD(绑卡阶段)、APPLY（进入准备借款阶段之前）、LOAN_REDAY（借款准备阶段）、LOAN(已借款阶段)
     * 提交授信前：小米金融用户服务协议 小米贷款隐私协议 个人征信授权书
     * 提交借款前：贷款协议 试算
     * 借款祥情页面：贷款协议 放款后
     *
     */
    @Test
//    @Rollback
    public void queryProtocolTestCredit() {
//        UserVO userVO = userService.getUserInfo(customerCode);
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ResultDTO resultDTO = creditXmjrService.queryProtocol(customerVO, partnerVO, EnumProtocol.OPERATOR_TYPE_CREDIT, null, null, null);
//        log.debug("授信协议查询resultDTO:{}", resultDTO);
//        Assert.assertEquals(true, resultDTO.checkSuccess());
    }

    /**
     * 第四步 发起授信申请 -- 请求成功
     */
    @Test
    public void creditApplyTest() {
//        String key = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX + customerCode);
//        redisUtilService.del(key);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ResultDTO resultDTO = creditXmjrService.applyCredit(customerVO, partnerVO, null);
//        Assert.assertEquals(true, resultDTO.checkSuccess());
//        redisUtilService.del(key);
    }

    /**
     * 第五步 主动查询授信结果
     */
    @Test
    public void getCreditStatusQueryTest() {
//        String key = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX + customerCode);
//        String redisKey = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX + customerCode);
//        redisUtilService.del(key);
//        redisUtilService.del(redisKey);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询贷超平台用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        creditXmjrService.getCreditStatus(customerVO, partnerVO);
//        redisUtilService.del(key);
//        redisUtilService.del(redisKey);
    }

    /**
     * 第五步 用户额度查询结果
     */
    @Test
    public void queryQuotTest() {
//        String key = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX + customerCode);
//        String redisKey = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX + customerCode);
//        redisUtilService.del(key);
//        redisUtilService.del(redisKey);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//
//        XmjrCreditAmountRequestDTO xmjrCreditAmountRequestDTO = new XmjrCreditAmountRequestDTO();
//        xmjrCreditAmountRequestDTO.setOpenId(customerCode);
//        String recordId = SerialNumberUtil.getPrimaryId();
//        XmjrResultDTO quotResultDTO = xmjrCreditService.creditAmountQuery(recordId, xmjrCreditAmountRequestDTO);
//
//        if (quotResultDTO.checkSuccess()) {
//            XmjrCreditAmountResponseDTO xmjrCreditAmountResponseDTO = (XmjrCreditAmountResponseDTO) quotResultDTO.getData();
//            if (null != xmjrCreditAmountResponseDTO) {
//                log.debug("客户编码：{}，小米额度查询结果为：{}", customerCode, xmjrCreditAmountResponseDTO);
//            }
//        }
//
//
//        redisUtilService.del(key);
//        redisUtilService.del(redisKey);
    }

    /**
     *
     * 第六步、获取小米支持的银行卡列表
     */
    @Test
    public void getSupportBankList() {
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ResultDTO supportBankList = bankCardXmjrService.getSupportBankList(partnerVO, customerCode);
//        log.debug("supportBankList:{}", supportBankList);
//        Assert.assertEquals(supportBankList.checkSuccess(), Boolean.TRUE);
    }

    /**
     * 预绑卡发送短信
     */
    @Test
    public void sendSms() {
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        BankCardSendSmsRequestDTO bankCardSendSmsRequestDTO = new BankCardSendSmsRequestDTO();
//        bankCardSendSmsRequestDTO.setCertificateNo(customerVO.getIdentityNo());
//        bankCardSendSmsRequestDTO.setBankCardNo(bankNo);
//        bankCardSendSmsRequestDTO.setPhoneNo(customerVO.getPhoneNo());
//        bankCardSendSmsRequestDTO.setRealName(customerVO.getCustomerName());
//        bankCardSendSmsRequestDTO.setCustomerCode(customerVO.getCustomerCode());
//        bankCardSendSmsRequestDTO.setPartnerCode(partnerVO.getPartnerCode());
//        ResultDTO resultDTO = bankCardXmjrService.sendSms(customerVO, partnerVO, bankCardSendSmsRequestDTO);
//
//        Assert.assertEquals(Boolean.TRUE, resultDTO.checkSuccess());
    }

    /**
     * 绑卡签约
     */
    @Test
    public void signTest() {
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//
//        BankCardSignRequestDTO bankCardSignRequestDTO = new BankCardSignRequestDTO();
//        bankCardSignRequestDTO.setRealName(customerVO.getCustomerName());
//        bankCardSignRequestDTO.setPhoneNo(customerVO.getPhoneNo());
//        bankCardSignRequestDTO.setBankCardNo(bankNo);
//        bankCardSignRequestDTO.setCertificateNo(customerVO.getIdentityNo());
//        bankCardSignRequestDTO.setBankName("工商银行");
//        bankCardSignRequestDTO.setBankCode("ICBC");
//        bankCardSignRequestDTO.setSmsCode("000000");
//        ResultDTO sign = bankCardXmjrService.sign(customerVO, partnerVO, bankCardSignRequestDTO);
//        Assert.assertEquals(sign.checkSuccess(), Boolean.TRUE);
    }

    /**
     * 用户已绑卡接口查询
     */
    @Test
    public void bankCardListTest() {
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//
//       /* BankCardSignRequestDTO bankCardSignRequestDTO = new BankCardSignRequestDTO();
//        bankCardSignRequestDTO.setRealName(customerVO.getCustomerName());
//        bankCardSignRequestDTO.setPhoneNo(customerVO.getPhoneNo());
//        bankCardSignRequestDTO.setBankCardNo("6214834113431192");
//        bankCardSignRequestDTO.setCertificateNo(customerVO.getIdentityNo());
//        bankCardSignRequestDTO.setBankName("招商银行");
//        bankCardSignRequestDTO.setBankCode("CMB");
//        bankCardSignRequestDTO.setSmsCode("111111");*/
//        ResultDTO sign = bankCardXmjrService.getBankCardList(customerVO, partnerVO);
//        Assert.assertEquals(sign.checkSuccess(), Boolean.TRUE);

    }

    /**
     * 获取协议列表(借款准备阶段)
     */
    @Test
//    @Rollback
    public void queryProtocolTestLoanReady() {
//        UserVO userVO = userService.getUserInfo(customerCode);
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ResultDTO resultDTO = creditXmjrService.queryProtocol(customerVO, partnerVO, EnumProtocol.OPERATOR_TYPE_LOAN_READY, bankNo, null, null);
//        log.debug("借款协议resultDTO:{}", resultDTO);
    }


    /**
     * 获取协议列表(已借款阶段)
     */
    @Test
//    @Rollback
    public void queryProtocolTestLoan() {
//        UserVO userVO = userService.getUserInfo(customerCode);
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ResultDTO resultDTO = creditXmjrService.queryProtocol(customerVO, partnerVO, EnumProtocol.OPERATOR_TYPE_LOAN, bankNo, null, null);
//        log.debug("借款协议resultDTO:{}", resultDTO);
    }

    /**
     * 获取借款准备数据
     */
    @Test
    public void obtainLoanDataTest() {
//        String key = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX + customerCode);
//        String redisKey = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX + customerCode);
//        redisUtilService.del(key);
//        redisUtilService.del(redisKey);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        loanXmjrService.obtainLoanData(customerVO, partnerVO);
//        redisUtilService.del(key);
//        redisUtilService.del(redisKey);
    }

    /**
     * 借款试算接口
     */
    @Test
    public void loanTrialTest() {
//        String key = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX +  customerCode);
//        String redisKey = redisUtilService.getRedisKey(ConstRedisParam.XMJR_CREDIT_PREFIX  + customerCode);
//        redisUtilService.del(key);
//        redisUtilService.del(redisKey);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        CalRentListRequestDTO calRentListRequestDTO = new CalRentListRequestDTO();
//        calRentListRequestDTO.setCustomerCode(customerCode);
//        calRentListRequestDTO.setLeaseCash("1000");
////        calRentListRequestDTO.setPaymentFrequency();
////        calRentListRequestDTO.setPuttingDate();
////        calRentListRequestDTO.setRepaymentDay();
//        calRentListRequestDTO.setRepaymentMethod(XmjrEnumRepaymentMethod.EQUAL_AMOUNT);
//        calRentListRequestDTO.setTotalMonths("12");
//        loanXmjrService.loanTrial(customerVO, partnerVO, calRentListRequestDTO);
//        redisUtilService.del(key);
//        redisUtilService.del(redisKey);
    }

    /**
     * 小米提现接口
     */
    @Test
    public void launchCashLoanTest() {
//        String redisKey = ConstRedisParam.XMJR_LOAN_PREFIX  + customerCode;
//        redisUtilService.del(redisKey);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//
//        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
//        loanRequestDTO.setAccountNo(bankNo);
//        loanRequestDTO.setCustomerCode(customerCode);
////        loanRequestDTO.setInterestFreeId();
//        loanRequestDTO.setLoanAmount("5000");
//        loanRequestDTO.setLoanInvestment("01");
//        loanRequestDTO.setMonthlyNumber(6);
//        loanRequestDTO.setPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
////        loanRequestDTO.setProductCode();
//        loanRequestDTO.setRepaymentDay("11");
//        // TODO 此处的还款方式取借款准备数据中的还款方式编码
//        loanRequestDTO.setRepaymentMethod("EQUAL_RENT");
//        ResultDTO resultDTO = loanXmjrService.launchCashLoan(customerVO, partnerVO, loanRequestDTO);
//        Assert.assertEquals(resultDTO.checkSuccess(), Boolean.TRUE);
    }

    /**
     * 小米借款结果主动查询接口
     */
    @Test
    public void loanResultQueryTest() {
//        String redisKey1 = ConstRedisParam.XMJR_LOAN_PREFIX + customerCode;
//        String redisKey2 = ConstRedisParam.XMJR_LOAN_PREFIX +  customerCode;
//        redisUtilService.del(redisKey1);
//        redisUtilService.del(redisKey2);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ContractInfoVOExample customerVOExample = new ContractInfoVOExample();
//        ContractInfoVOExample.Criteria criteria = customerVOExample.createCriteria();
//        //每次查询的时候需要把放款生成的合同号改动
//        criteria.andContractCodeEqualTo("1597196974857469");
//        List<ContractInfoVO> contractInfoVOs = contractInfoDAO.selectByExample(customerVOExample);
//        ResultDTO resultDTO = loanXmjrService.loanResult(customerVO, partnerVO, contractInfoVOs.get(0));
//        Assert.assertEquals(resultDTO.checkSuccess(), Boolean.TRUE);
    }

    /**
     * 小米主动还款接口
     */
    @Test
    public void activeRepaymentTest() {
//        String redisKey1 = ConstRedisParam.XMJR_REPAYMENT_PREFIX  + customerCode;
//        String redisKey2 = ConstRedisParam.XMJR_REPAYMENT_PREFIX  + customerCode;
//        redisUtilService.del(redisKey1);
//        redisUtilService.del(redisKey2);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ContractInfoVOExample customerVOExample = new ContractInfoVOExample();
//        ContractInfoVOExample.Criteria criteria = customerVOExample.createCriteria();
//        //每次查询的时候需要把放款生成的合同号改动
//        criteria.andContractCodeEqualTo("1597219687199511");
//        List<ContractInfoVO> contractInfoVOs = contractInfoDAO.selectByExample(customerVOExample);
//        ResultDTO resultDTO = repaymentXmjrService.agentPay(customerVO.getCustomerId(), customerVO.getCustomerCode(), partnerVO,null);
//        Assert.assertEquals(resultDTO.checkSuccess(), Boolean.TRUE);
    }

    /**
     * 小米还款结果查询接口
     */
    @Test
    public void repayResultTest() {
//        String redisKey1 = ConstRedisParam.XMJR_REPAYMENT_PREFIX +  customerCode;
//        String redisKey2 = ConstRedisParam.XMJR_REPAYMENT_PREFIX +  customerCode;
//        redisUtilService.del(redisKey1);
//        redisUtilService.del(redisKey2);
//        UserVO userVO = userService.getUserInfo(customerCode);
//        log.debug(userVO.toString());
//        // 保存或者查询用户信息
//        CustomerVO customerVO = customerInfoService.saveCustomerInfo(userVO);
//        partnerService.customerAccess(customerCode, EnumPartnerCode.PARTNER_XMJR_API);
//        log.debug(userVO.toString());
//        PartnerVO partnerVO = partnerDAO.selectByPartnerCode(EnumPartnerCode.PARTNER_XMJR_API);
//        ContractInfoVOExample customerVOExample = new ContractInfoVOExample();
//        ContractInfoVOExample.Criteria criteria = customerVOExample.createCriteria();
//        //每次查询的时候需要把放款生成的合同号改动
//        criteria.andContractCodeEqualTo("1597133689663446");
//        List<ContractInfoVO> contractInfoVOs = contractInfoDAO.selectByExample(customerVOExample);
//
//        RepaymentRecordVO repaymentRecordVO = repaymentRecordDAO.selectByPrimaryKey("9332f68c-58a1-46af-a659-5b70eadfbf27");
//        ResultDTO resultDTO = repaymentXmjrService.repayResult(customerVO, partnerVO, repaymentRecordVO);
//        Assert.assertEquals(resultDTO.checkSuccess(), Boolean.TRUE);
    }

    @Test
    public void testXiaoMiActiveUser(){
//        int isActiveUser = creditXmjrService.queryActiveUserFromDataGroup(customerCode);
    }
}

