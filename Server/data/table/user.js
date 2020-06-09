module.exports = (sequelize, DataTypes) => {
    return sequelize.define('user', {
        user_id: {
            type: DataTypes.STRING(10),
            primaryKey: true,
            allowNull: false
        },
        pwd: {
            type: DataTypes.STRING(6000),
            allowNull: false
        },
        salt: {
            type: DataTypes.STRING(6000),
            allowNull: false
        },
        grade: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        name: {
            type: DataTypes.STRING(30),
            allowNull: false
        },
        department: {
            type: DataTypes.STRING(45),
            allowNull: false
        },
        signup_time: {
            type: DataTypes.STRING(30),
            allowNull: false
        }
    },{
        timestamps: false,
        tableName: 'user'
    })
}