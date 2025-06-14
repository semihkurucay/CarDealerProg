USE [db_CarDealer]
GO
/****** Object:  User [user]    Script Date: 12.06.2025 00:46:52 ******/
CREATE USER [user] FOR LOGIN [user] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [user]
GO
/****** Object:  Table [dbo].[tbl_Brands]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Brands](
	[brn_ID] [int] IDENTITY(1,1) NOT NULL,
	[brn_Name] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_tbl_Brands] PRIMARY KEY CLUSTERED 
(
	[brn_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_CarDetial]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_CarDetial](
	[dtl_car_VIN] [nvarchar](50) NOT NULL,
	[dtl_Packet] [nvarchar](20) NULL,
	[dtl_KM] [nvarchar](20) NULL,
	[dtl_Plate] [nvarchar](10) NULL,
	[dtl_Price] [nvarchar](50) NULL,
	[dtl_WebUrl] [nvarchar](500) NULL,
	[dtl_Report] [bit] NULL,
	[dtl_Comment] [nvarchar](500) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Cars]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Cars](
	[car_VIN] [nvarchar](50) NOT NULL,
	[car_brn_ID] [int] NOT NULL,
	[car_mdl_ID] [int] NOT NULL,
	[car_Year] [nvarchar](4) NOT NULL,
	[car_IsActive] [bit] NULL,
 CONSTRAINT [PK_tbl_Cars] PRIMARY KEY CLUSTERED 
(
	[car_VIN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_HistoryCar]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_HistoryCar](
	[hst_ID] [nvarchar](10) NOT NULL,
	[hst_VIN] [nvarchar](50) NOT NULL,
	[hst_Packet] [nvarchar](20) NULL,
	[hst_Km] [nvarchar](20) NULL,
	[hst_Plate] [nvarchar](10) NOT NULL,
	[hst_Price] [nvarchar](50) NOT NULL,
	[hst_WebUrl] [nvarchar](500) NULL,
	[hst_Report] [bit] NULL,
	[hst_Comment] [nvarchar](500) NULL,
 CONSTRAINT [PK_tbl_HistoryCar] PRIMARY KEY CLUSTERED 
(
	[hst_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Login]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Login](
	[lgn_ID] [int] IDENTITY(1,1) NOT NULL,
	[lgn_Username] [nvarchar](20) NOT NULL,
	[lgn_Password] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_tbl_Login] PRIMARY KEY CLUSTERED 
(
	[lgn_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_MailMessage]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_MailMessage](
	[mes_ID] [int] NOT NULL,
	[mes_Title] [nvarchar](50) NULL,
	[mes_Front] [nvarchar](1000) NULL,
	[mes_Back] [nvarchar](1000) NULL,
 CONSTRAINT [PK_tbl_MailMessage] PRIMARY KEY CLUSTERED 
(
	[mes_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_MailSetting]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_MailSetting](
	[mal_ID] [int] NOT NULL,
	[mal_MailAddress] [nvarchar](50) NULL,
	[mal_Password] [nvarchar](50) NULL,
	[mal_Port] [nvarchar](5) NULL,
	[mal_Host] [nvarchar](50) NULL,
	[mal_Auth] [bit] NULL,
	[mal_Starttls] [bit] NULL,
 CONSTRAINT [PK_tbl_MailSetting] PRIMARY KEY CLUSTERED 
(
	[mal_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Models]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Models](
	[mdl_ID] [int] IDENTITY(1,1) NOT NULL,
	[mdl_brn_ID] [int] NOT NULL,
	[mdl_Name] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_tbl_Models] PRIMARY KEY CLUSTERED 
(
	[mdl_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Process]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Process](
	[prc_ID] [int] IDENTITY(1,1) NOT NULL,
	[prc_hst_ID] [nvarchar](10) NOT NULL,
	[prc_usr_ID] [nvarchar](15) NOT NULL,
	[prc_Price] [nvarchar](20) NOT NULL,
	[prc_Title] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_tbl_Proces] PRIMARY KEY CLUSTERED 
(
	[prc_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Users]    Script Date: 12.06.2025 00:46:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Users](
	[usr_ID] [nvarchar](15) NOT NULL,
	[usr_NameSurname] [nvarchar](50) NOT NULL,
	[usr_TaxOffice] [nvarchar](20) NULL,
	[usr_Phone] [nvarchar](15) NOT NULL,
	[usr_Mail] [nvarchar](50) NULL,
	[usr_City] [nvarchar](20) NOT NULL,
	[usr_District] [nvarchar](20) NOT NULL,
	[usr_Address] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_tbl_Users] PRIMARY KEY CLUSTERED 
(
	[usr_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tbl_Cars] ADD  CONSTRAINT [DF_tbl_Cars_car_IsActive]  DEFAULT ((0)) FOR [car_IsActive]
GO
ALTER TABLE [dbo].[tbl_HistoryCar] ADD  CONSTRAINT [DF_Table_1_car_Report]  DEFAULT ((0)) FOR [hst_Report]
GO
ALTER TABLE [dbo].[tbl_CarDetial]  WITH CHECK ADD  CONSTRAINT [FK_tbl_CarDetial_tbl_Cars] FOREIGN KEY([dtl_car_VIN])
REFERENCES [dbo].[tbl_Cars] ([car_VIN])
GO
ALTER TABLE [dbo].[tbl_CarDetial] CHECK CONSTRAINT [FK_tbl_CarDetial_tbl_Cars]
GO
ALTER TABLE [dbo].[tbl_Cars]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Cars_tbl_Brands] FOREIGN KEY([car_brn_ID])
REFERENCES [dbo].[tbl_Brands] ([brn_ID])
GO
ALTER TABLE [dbo].[tbl_Cars] CHECK CONSTRAINT [FK_tbl_Cars_tbl_Brands]
GO
ALTER TABLE [dbo].[tbl_Cars]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Cars_tbl_Models] FOREIGN KEY([car_mdl_ID])
REFERENCES [dbo].[tbl_Models] ([mdl_ID])
GO
ALTER TABLE [dbo].[tbl_Cars] CHECK CONSTRAINT [FK_tbl_Cars_tbl_Models]
GO
ALTER TABLE [dbo].[tbl_Models]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Models_tbl_Brands] FOREIGN KEY([mdl_brn_ID])
REFERENCES [dbo].[tbl_Brands] ([brn_ID])
GO
ALTER TABLE [dbo].[tbl_Models] CHECK CONSTRAINT [FK_tbl_Models_tbl_Brands]
GO
ALTER TABLE [dbo].[tbl_Process]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Process_tbl_HistoryCar] FOREIGN KEY([prc_hst_ID])
REFERENCES [dbo].[tbl_HistoryCar] ([hst_ID])
GO
ALTER TABLE [dbo].[tbl_Process] CHECK CONSTRAINT [FK_tbl_Process_tbl_HistoryCar]
GO
ALTER TABLE [dbo].[tbl_Process]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Process_tbl_Users] FOREIGN KEY([prc_usr_ID])
REFERENCES [dbo].[tbl_Users] ([usr_ID])
GO
ALTER TABLE [dbo].[tbl_Process] CHECK CONSTRAINT [FK_tbl_Process_tbl_Users]
GO
